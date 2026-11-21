package com.example.rpg.equipment;

import com.example.rpg.enums.EquipmentRarity;
import com.example.rpg.enums.EquipmentType;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Armor extends Equipment {
    protected int defense;


    public Armor(String name, EquipmentType type, EquipmentRarity rarity, int requiredStrength, int requiredDexterity, int requiredIntelligence, int defense) {
        super(name, type, rarity, requiredStrength, requiredDexterity, requiredIntelligence);
        this.defense = defense;
    }

    protected Armor() {
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
