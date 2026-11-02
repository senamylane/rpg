package com.example.rpg.services;

import com.example.rpg.enums.BattleStatus;
import com.example.rpg.enums.Zone;
import com.example.rpg.gameCharacter.GameCharacter;
import com.example.rpg.gameElements.Battle;
import com.example.rpg.gameElements.Combatant;
import com.example.rpg.gameElements.Enemy;
import com.example.rpg.jpa.BattleRepository;
import com.example.rpg.jpa.CharacterRepository;
import com.example.rpg.jpa.EnemyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BattleManagerService {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private EnemyRepository enemyRepository;
    @Autowired
    private BattleRepository battleRepository;
    private GameCharacter player;
    private List<Enemy> enemies;

    public BattleManagerService() {
    }

    @Transactional
    public Battle initBattle(Long characterId) {
        // 1. Prevent duplicate active encounters for the same character
        return battleRepository.findByPlayerIdAndStatus(characterId, BattleStatus.IN_PROGRESS)
                .orElseGet(() -> {
                    GameCharacter player = characterRepository.findById(characterId)
                            .orElseThrow(() -> new IllegalArgumentException("Character not found with ID: " + characterId));

                    Zone currentZone = player.getCurrentZone();
                    List<Enemy> possibleEnemies = enemyRepository.findBySpawnZone(currentZone);

                    if (possibleEnemies.isEmpty()) {
                        throw new IllegalStateException("No enemies configured for zone: " + currentZone);
                    }

                    List<Enemy> spawnedEnemies = new ArrayList<>();
                    int numEnemies = 1 + ThreadLocalRandom.current().nextInt(6);

                    for (int i = 0; i < numEnemies; i++) {
                        int randomIndex = ThreadLocalRandom.current().nextInt(possibleEnemies.size());
                        Enemy enemyTemplate = possibleEnemies.get(randomIndex);
                        UUID id = UUID.randomUUID();

                        spawnedEnemies.add(enemyTemplate.copy(id));
                    }

                    Battle battle = new Battle(player, spawnedEnemies);
                    return battleRepository.save(battle);
                });
    }

    @Transactional
    public Battle endBattle(UUID battleId) {

        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new IllegalArgumentException("Battle not found with ID: " + battleId));

        if (battle.isFinished()) {
            return battle;
        }

        GameCharacter player = battle.getPlayer();


        int totalExpGained = 0;
        for (Enemy enemy : battle.getEnemies()) {
            if (enemy.getHp() <= 0) {
                totalExpGained += enemy.getExpGiven();
            }
        }
        player.addExperience(totalExpGained);


        boolean allEnemiesDefeated = battle.getEnemies().stream()
                .allMatch(enemy -> enemy.getHp() <= 0);

        battle.setStatus(allEnemiesDefeated ? BattleStatus.VICTORY : BattleStatus.DEFEAT);


        characterRepository.save(player);
        return battleRepository.save(battle);
    }

    public void attack(Combatant attacker, Combatant target) {
        attacker.attack(target);
    }

    public void useSpell(Combatant attacker, Combatant target) {
        attacker.useSpell(target);
    }

    @Transactional()
    public Enemy getEnemyById(UUID battleId, UUID enemyId) {
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new IllegalArgumentException("Battle not found with ID: " + battleId));

        if (battle.isFinished()) {
            throw new IllegalStateException("Cannot query enemies from a finished battle.");
        }

        return battle.getEnemies().stream()
                .filter(enemy -> enemy.getEnemy_id().equals(enemyId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enemy found with ID " + enemyId + " in battle " + battleId));
    }


    public Battle getActiveBattle(Long characterId) {
        return battleRepository.findByPlayerIdAndStatus(characterId, BattleStatus.IN_PROGRESS)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No active battle found for character ID: " + characterId
                ));
    }
}
