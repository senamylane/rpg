package com.example.rpg.controllers;

import com.example.rpg.gameCharacter.GameCharacter;
import com.example.rpg.gameElements.Battle;
import com.example.rpg.gameElements.Enemy;
import com.example.rpg.jpa.CharacterRepository;
import com.example.rpg.services.BattleManagerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/battle")
public class BattleManagerController {
    @Autowired
    private BattleManagerService battleManagerService;
    @Autowired
    private CharacterRepository characterRepository;

    @PostMapping(path = "/init") // Map ONLY POST Requests
    @Transactional
    public @ResponseBody Battle initBattle(@RequestParam Long character_id) {
        return battleManagerService.initBattle(character_id);
    }

    @PostMapping(path = "/end/{battle_id}") // Map ONLY POST Requests
    @Transactional
    public @ResponseBody Battle initBattle(@PathVariable UUID battle_id) {
        return battleManagerService.endBattle(battle_id);
    }

    @PostMapping(path = "/player-attack") // Map ONLY POST Requests
    @Transactional
    public @ResponseBody Enemy player_attack(@RequestParam UUID battle_id, Long attacker_id, @RequestParam UUID target_id) {
        GameCharacter player = characterRepository.findById(attacker_id)
                .orElseThrow(() -> new IllegalArgumentException("Character not found"));
        Enemy enemy = battleManagerService.getEnemyById(battle_id, target_id);
        battleManagerService.attack(player, enemy);

        return enemy;
    }

    @PostMapping(path = "/enemy-attack") // Map ONLY POST Requests
    @Transactional
    public @ResponseBody GameCharacter enemy_attack(@RequestParam UUID battle_id, UUID attacker_id, @RequestParam Long target_id) {
        Enemy enemy = battleManagerService.getEnemyById(battle_id, attacker_id);
        GameCharacter player = characterRepository.findById(target_id)
                .orElseThrow(() -> new IllegalArgumentException("Character not found"));
        battleManagerService.attack(enemy, player);

        return player;
    }

    @GetMapping("/active/{characterId}")
    public ResponseEntity<Battle> getActiveBattle(@PathVariable Long characterId) {
        Battle battle = battleManagerService.getActiveBattle(characterId);
        return ResponseEntity.ok(battle);
    }
}
