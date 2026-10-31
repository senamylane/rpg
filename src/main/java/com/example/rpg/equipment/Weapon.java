package com.example.rpg.equipment;

import jakarta.persistence.Entity;

@Entity
public class Weapon extends Equipment {
    protected int minimumDamage;
    protected int maximumDamage;
    protected int minimumMagicDamage;
    protected int maximumMagicDamage;
}



