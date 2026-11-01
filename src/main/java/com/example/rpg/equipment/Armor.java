package com.example.rpg.equipment;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Armor extends Equipment {
    protected int defense;
}
