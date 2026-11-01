package com.example.rpg.equipment;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String name;
    protected String description;
    protected String imagePath;
    protected int requiredStrength;
    protected int requiredDexterity;
    protected int requiredIntelligence;

    protected void equip() {

    }

    protected void unequip() {

    }
}
