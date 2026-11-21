package com.example.rpg.services;

import com.example.rpg.dto.CharacterSpellRequest;
import com.example.rpg.enums.BattleStatus;
import com.example.rpg.enums.Spell;
import com.example.rpg.enums.Zone;
import com.example.rpg.gameCharacter.Enemy;
import com.example.rpg.gameCharacter.GameCharacter;
import com.example.rpg.gameCharacter.Hero;
import com.example.rpg.gameElements.Battle;
import com.example.rpg.jpa.BattleRepository;
import com.example.rpg.jpa.CharacterRepository;
import com.example.rpg.jpa.EnemyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
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
    private static int MAX_NUMBER_ENEMIES = 6;
    @Autowired
    private CharacterService characterService;
    private List<Enemy> enemies;
    private Hero player;

    public BattleManagerService() {
    }

    @Transactional
    public Battle initBattle(Long characterId) {
        // 1. Prevent duplicate active encounters for the same character
        return battleRepository.findByPlayerIdAndStatus(characterId, BattleStatus.IN_PROGRESS)
                .orElseGet(() -> {
                    Hero player = characterRepository.findById(characterId)
                            .orElseThrow(() -> new IllegalArgumentException("Character not found with ID: " + characterId));

                    Zone currentZone = player.getCurrentZone();
                    List<Enemy> possibleEnemies = enemyRepository.findBySpawnZone(currentZone);

                    if (possibleEnemies.isEmpty()) {
                        throw new IllegalStateException("No enemies configured for zone: " + currentZone);
                    }

                    List<Enemy> spawnedEnemies = new ArrayList<>();
                    this.calculate_max_number_enemies(player);
                    int numEnemies = 1 + ThreadLocalRandom.current().nextInt(MAX_NUMBER_ENEMIES);

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

    private void calculate_max_number_enemies(Hero player) {
        int level = player.getLevel();
        int minEnemies = ((level - 1) / 10) + 1;

        int maxLimit = ((level - 1) / 10) + 2;
        MAX_NUMBER_ENEMIES = Math.min(6, Math.max(minEnemies, maxLimit));
    }

    @Transactional
    public Battle endBattle(UUID battleId) {

        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new IllegalArgumentException("Battle not found with ID: " + battleId));

        if (battle.isFinished()) {
            return battle;
        }

        Hero player = battle.getPlayer();

        int totalExpGained = 0;
        for (Enemy enemy : battle.getEnemies()) {
            if (enemy.getHp() <= 0) {
                totalExpGained += enemy.getExpGiven();
            }
        }
        characterService.addExperienceAndProcessLevelUp(player, totalExpGained);

        boolean allEnemiesDefeated = battle.getEnemies().stream()
                .allMatch(enemy -> enemy.getHp() <= 0);

        battle.setStatus(allEnemiesDefeated ? BattleStatus.VICTORY : BattleStatus.DEFEAT);
        battle.clearEnemies();

        characterRepository.save(player);
        return battleRepository.save(battle);
    }

    public void attack(GameCharacter attacker, GameCharacter target) {
        attacker.attack(target);
    }

    public void useSpell(GameCharacter attacker, List<GameCharacter> targets, Spell spell) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        attacker.useSpell(spell, targets);
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

    @Transactional
    public List<GameCharacter> processSpell(CharacterSpellRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Battle battle = battleRepository.findById(request.getBattle_id())
                .orElseThrow(() -> new IllegalArgumentException("Battle not found"));

        Hero player = characterRepository.findById(request.getCharacter_id())
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        Spell spell = Spell.valueOf(request.getSpell());

        if (player.getClassType() != spell.getClassType()) {
            throw new IllegalStateException(player.getName() + " não pode conjurar magias da classe " + spell.getClassType());
        }

        if (player.getMana() < spell.getManaCost()) {
            throw new IllegalStateException("Not enough mana");
        }

        List<GameCharacter> enemies = new ArrayList<>();
        int maxTargets = spell.getNumTargets();
        for (UUID uuid : request.getTargets()) {
            if (maxTargets <= 0) {
                break;
            }
            Enemy targetEnemy = battle.getEnemies().stream()
                    .filter(e -> e.getEnemy_id().equals(uuid)) // assumindo que targetId é o UUID
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Enemy not found"));
            enemies.add(targetEnemy);
        }


        player.setMana(player.getMana() - spell.getManaCost());

        player.useSpell(spell, enemies);

        characterRepository.save(player);
        battleRepository.save(battle);

        return enemies;
    }
}
