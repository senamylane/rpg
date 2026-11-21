package com.example.rpg.controllers;

import com.example.rpg.dto.CharacterSpellRequest;
import com.example.rpg.enums.ClassType;
import com.example.rpg.enums.Spell;
import com.example.rpg.gameCharacter.Enemy;
import com.example.rpg.gameCharacter.GameCharacter;
import com.example.rpg.gameElements.Battle;
import com.example.rpg.gameElements.SpellDTO;
import com.example.rpg.jpa.BattleRepository;
import com.example.rpg.jpa.CharacterRepository;
import com.example.rpg.services.BattleManagerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/battle")
public class BattleManagerController {
    @Autowired
    private BattleManagerService battleManagerService;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private BattleRepository battleRepository;

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

    @PostMapping(path = "/player/use-spell") // Map ONLY POST Requests
    @Transactional
    public @ResponseBody List<GameCharacter> player_spell(@Valid @RequestBody CharacterSpellRequest payload) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return battleManagerService.processSpell(payload);
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

    @GetMapping("/{battleId}")
    public ResponseEntity<Battle> getBattle(@PathVariable UUID battleId) {
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new IllegalArgumentException("Batalha não encontrada"));
        return ResponseEntity.ok(battle);
    }

    @GetMapping("/{classType}/spells")
    public ResponseEntity<List<SpellDTO>> getSpellsByClass(@PathVariable ClassType classType) {
        List<SpellDTO> spells = Arrays.stream(Spell.values())
                .filter(spell -> spell.getClassType().name().equals(classType.name()))
                .map(spell -> new SpellDTO(
                        spell.name(),
                        spell.getName(),
                        spell.getManaCost(),
                        spell.getNumTargets()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(spells);
    }
}
