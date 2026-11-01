package com.example.rpg.equipment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;

import java.util.concurrent.ThreadLocalRandom;

@JsonPropertyOrder({"name", "minimumDamage", "maximumDamage", "minimumMagicDamage", "maximumMagicDamage"})
@Entity
public class Weapon extends Equipment {
    protected int minimumDamage;
    protected int maximumDamage;
    protected int minimumMagicDamage;
    protected int maximumMagicDamage;

    public Weapon(String name, int requiredStrength, int requiredDexterity, int requiredIntelligence, int minimumMagicDamage, int minimumDamage) {
        super(name, requiredStrength, requiredDexterity, requiredIntelligence);

        this.minimumMagicDamage = minimumMagicDamage;
        int variation = ThreadLocalRandom.current().nextInt(1, 11);
        this.maximumMagicDamage = this.minimumMagicDamage + variation;

        this.minimumDamage = minimumDamage;
        variation = ThreadLocalRandom.current().nextInt(1, 11);
        this.maximumDamage = this.minimumDamage + variation;
    }

    protected Weapon() {
        super("", 1, 1, 1);
    }

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

    public int getMinimumDamage() {
        return minimumDamage;
    }

    public void setMinimumDamage(int minimumDamage) {
        this.minimumDamage = minimumDamage;
    }

    public int getMaximumDamage() {
        return maximumDamage;
    }

    public void setMaximumDamage(int maximumDamage) {
        this.maximumDamage = maximumDamage;
    }

    public int getMinimumMagicDamage() {
        return minimumMagicDamage;
    }

    public void setMinimumMagicDamage(int minimumMagicDamage) {
        this.minimumMagicDamage = minimumMagicDamage;
    }

    public int getMaximumMagicDamage() {
        return maximumMagicDamage;
    }

    public void setMaximumMagicDamage(int maximumMagicDamage) {
        this.maximumMagicDamage = maximumMagicDamage;
    }
}



