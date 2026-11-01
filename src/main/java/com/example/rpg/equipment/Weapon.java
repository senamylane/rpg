package com.example.rpg.equipment;

import jakarta.persistence.Entity;

@Entity
public class Weapon extends Equipment {
    protected int minimumDamage;
    protected int maximumDamage;
    protected int minimumMagicDamage;
    protected int maximumMagicDamage;

    @Override
    public String toString() {
        return "Weapon{" +
                "minimumDamage=" + minimumDamage +
                ", maximumDamage=" + maximumDamage +
                ", minimumMagicDamage=" + minimumMagicDamage +
                ", maximumMagicDamage=" + maximumMagicDamage +
                ", name='" + name + '\'' +
                ", requiredStrength=" + requiredStrength +
                ", requiredDexterity=" + requiredDexterity +
                ", requiredIntelligence=" + requiredIntelligence +
                ", description='" + description + '\'' +
                '}';
    }
}



