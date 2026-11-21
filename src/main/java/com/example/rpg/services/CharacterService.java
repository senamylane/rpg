package com.example.rpg.services;

import com.example.rpg.gameCharacter.Hero;
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
    public void addExperienceAndProcessLevelUp(Hero character, int expGained) {
        character.setExperience(character.getExperience() + expGained);

        while (character.getExperience() >= character.getNextLevelExperience()) {

            if (character.getLevel() >= 50) {
                character.setExperience(0);
                break;
            }

            character.setExperience(character.getExperience() - character.getNextLevelExperience());

            character.setLevel(character.getLevel() + 1);

            // Aplica os bônus do novo nível
            character.setLevel(character.getLevel() + 1);
            character.setMaxHp(character.getMaxHp() + 20);
            character.setHp(character.getMaxHp());
            character.setMaxMana(character.getMaxMana() + 10);
            character.setMana(character.getMaxMana());
            character.setForce(character.getForce() + 3);
            character.setMagic(character.getMagic() + 1);
            character.setAttack(character.getAttack() + 3);
            character.setDefense(character.getDefense() + 1);

            character.setNextLevelExperience(100 + (character.getLevel() * 50));
        }
    }
}
