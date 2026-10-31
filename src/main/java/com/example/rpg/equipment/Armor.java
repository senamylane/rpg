package com.example.rpg.equipment;

import jakarta.persistence.Entity;

@Entity
public abstract class Armor extends Equipment {
    protected int defense;
}
