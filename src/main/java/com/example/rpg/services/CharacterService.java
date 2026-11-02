package com.example.rpg.services;

import com.example.rpg.gameCharacter.GameCharacter;
import com.example.rpg.jpa.CharacterRepository;
import com.example.rpg.jpa.EquipmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Transactional
    public GameCharacter processLevelUp(Long characterId) {
        GameCharacter character = characterRepository.findById(characterId)
                .orElseThrow(() -> new IllegalArgumentException("Character not found"));
        if (character.getLevel() == 50) {
            return character;
        }
        // Backend dictates the growth rules
        character.setLevel(character.getLevel() + 1);
        character.setMaxHp(character.getMaxHp() + 20);
        character.setHp(character.getMaxHp());
        character.setMana(character.getMana() + 10);
        character.setNextLevelExperience(100 + (character.getLevel() / 50 * 100));

        // .save() generically updates all the fields changed above
        return characterRepository.save(character);
    }
}
