package com.example.rpg.gameCharacter;

import jakarta.persistence.Entity;

@Entity
public class Mage extends GameCharacter {
    public Mage(String name) {
        super(name);
        this.health = 70;
        this.mana = 90;
    }

    protected Mage() {
    }
}
