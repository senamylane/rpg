package com.example.rpg.services;

import com.example.rpg.equipment.Equipment;
import com.example.rpg.gameCharacter.GameCharacter;
import com.example.rpg.jpa.CharacterRepository;
import com.example.rpg.jpa.EquipmentRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class EquipmentService {
    private final CharacterRepository characterRepository;
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(CharacterRepository characterRepository, EquipmentRepository equipmentRepository) {
        this.characterRepository = characterRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Transactional
    public boolean equipArmor(Long equipment_id, Long characterId) {
        GameCharacter character = characterRepository.findById(characterId)
                .orElseThrow(() -> new IllegalArgumentException("Character not found"));
        Optional<Equipment> equipment = equipmentRepository.findById(equipment_id);

        // Backend dictates the growth rules
        character.setLevel(character.getLevel() + 1);
        character.setMaxHp(character.getMaxHp() + 20);
        character.setHp(character.getMaxHp());
        character.setMana(character.getMana() + 10);
        character.setNextLevelExperience(100 + (character.getLevel() / 50 * 100));

        // .save() generically updates all the fields changed above
        return false;
    }
}
