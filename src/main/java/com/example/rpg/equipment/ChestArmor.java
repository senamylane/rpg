package com.example.rpg.equipment;

import com.example.rpg.enums.EquipmentRarity;
import com.example.rpg.enums.EquipmentType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;

@JsonPropertyOrder({"id", "name", "defense", "additionalConstitution"})
@Entity
public class ChestArmor extends Armor {
    protected int additionalConstitution;

    public ChestArmor(String name, EquipmentType type, EquipmentRarity rarity, int requiredStrength, int requiredDexterity, int requiredIntelligence, int defense, int additionalConstitution) {
        super(name, type, rarity, requiredStrength, requiredDexterity, requiredIntelligence, defense);
        this.additionalConstitution = additionalConstitution;
    }

    protected ChestArmor() {
        super();
    }

    public int getAdditionalConstitution() {
        return additionalConstitution;
    }

    public void setAdditionalConstitution(int additionalConstitution) {
        this.additionalConstitution = additionalConstitution;
    }
}
