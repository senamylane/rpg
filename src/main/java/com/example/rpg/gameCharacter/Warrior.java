package com.example.rpg.gameCharacter;

import jakarta.persistence.Entity;

@Entity
public class Warrior extends GameCharacter {

    public Warrior(String name) {
        super(name);
        this.health = 120;
        this.mana = 50;
    }

    protected Warrior() {
    }
}
