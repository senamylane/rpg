package com.example.rpg.equipment;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Armor extends Equipment {
    protected int defense;


    public Armor(String name, int requiredStrength, int requiredDexterity, int requiredIntelligence, int defense) {
        super(name, requiredStrength, requiredDexterity, requiredIntelligence);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
