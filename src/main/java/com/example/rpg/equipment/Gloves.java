package com.example.rpg.equipment;

import com.example.rpg.enums.EquipmentRarity;
import com.example.rpg.enums.EquipmentType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;

@JsonPropertyOrder({"id", "name", "defense", "additionalWisdom"})
@Entity
public class Gloves extends Armor {
    protected int additionalWisdom;

    protected Gloves() {
        super();
    }

    public Gloves(String name, EquipmentType type, EquipmentRarity rarity, int requiredStrength, int requiredDexterity, int requiredIntelligence, int defense, int additionalWisdom) {
        super(name, type, rarity, requiredStrength, requiredDexterity, requiredIntelligence, defense);
        this.additionalWisdom = additionalWisdom;
    }

    public int getAdditionalWisdom() {
        return additionalWisdom;
    }

    public void setAdditionalWisdom(int additionalWisdom) {
        this.additionalWisdom = additionalWisdom;
    }
}
