package com.example.rpg.services;

import com.example.rpg.enums.EquipmentType;
import com.example.rpg.equipment.Equipment;
import com.example.rpg.gameCharacter.GameCharacter;
import com.example.rpg.jpa.*;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipmentService {
    private static final Logger log = LogManager.getLogger(EquipmentService.class);
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private GlovesRepository glovesRepository;
    @Autowired
    private BootRepository bootRepository;
    @Autowired
    private ChestArmorRepository chestRepository;
    @Autowired
    private WeaponRepository weaponRepository;

    @Transactional
    public boolean pickUpItem(Long character_id, Long equipment_id, EquipmentType type) {
        GameCharacter character = characterRepository.findById(character_id).orElse(null);
        if (character == null) {
            return false;
        }
        boolean equipmentExists = false;
        switch (type) {
            case WEAPON -> equipmentExists = weaponRepository.existsById(equipment_id);
            case CHEST_ARMOR -> equipmentExists = chestRepository.existsById(equipment_id);
            case BOOTS -> equipmentExists = bootRepository.existsById(equipment_id);
            case GLOVES -> equipmentExists = glovesRepository.existsById(equipment_id);
        }

        if (!equipmentExists) {
            return false;
        }

        character.getInventory().put(equipment_id, type);

        return true;
    }

    @Transactional
    public boolean dropItem(Long character_id, Long equipment_id, EquipmentType type) {
        GameCharacter character = characterRepository.findById(character_id).orElse(null);
        if (character == null) {
            return false;
        }
        boolean removed = character.getInventory().remove(equipment_id, type);

        if (!removed) {
            return false; // The character didn't actually have this specific item
        }

        // 2. Explicitly save the parent entity to flush the ElementCollection deletion
        characterRepository.save(character);

        return true;
    }

    public List<Equipment> getCharacterInventory(Long character_id) {
        GameCharacter character = characterRepository.findById(character_id).orElse(null);
        List<Equipment> fullInventory = new ArrayList<>();
        if (character == null) {
            return fullInventory;
        }
        Map<Long, EquipmentType> inventoryIds = character.getInventory();
        log.info(inventoryIds);

        // If the list is empty, return immediately to save database calls
        if (inventoryIds == null || inventoryIds.isEmpty()) {
            return fullInventory;
        }


        for (Map.Entry<Long, EquipmentType> entry : inventoryIds.entrySet()) {
            Optional<? extends Equipment> equipment = Optional.empty();

            switch (entry.getValue()) {
                case WEAPON -> equipment = weaponRepository.findById(entry.getKey());
                case CHEST_ARMOR -> equipment = chestRepository.findById(entry.getKey());
                case BOOTS -> equipment = bootRepository.findById(entry.getKey());
                case GLOVES -> equipment = glovesRepository.findById(entry.getKey());
            }
            if (equipment.isPresent()) {
                Equipment item = equipment.get();
                fullInventory.add(equipment.get());
            }
        }
        log.info(fullInventory);

        return fullInventory;
    }
}
