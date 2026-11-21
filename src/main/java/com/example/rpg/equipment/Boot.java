package com.example.rpg.equipment;

import com.example.rpg.enums.EquipmentRarity;
import com.example.rpg.enums.EquipmentType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;

@JsonPropertyOrder({"id", "name", "defense", "additionalDexterity"})
@Entity
public class Boot extends Armor {
    protected int additionalDexterity;

    public Boot(String name, EquipmentType type, EquipmentRarity rarity, int requiredStrength, int requiredDexterity, int requiredIntelligence, int defense, int additionalDexterity) {
        super(name, type, rarity, requiredStrength, requiredDexterity, requiredIntelligence, defense);
        this.additionalDexterity = additionalDexterity;
    }

    protected Boot() {
        super();
    }

    public int getAdditionalDexterity() {
        return additionalDexterity;
    }

    public void setAdditionalDexterity(int additionalDexterity) {
        this.additionalDexterity = additionalDexterity;
    }
}
