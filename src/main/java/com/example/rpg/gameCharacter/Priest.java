package com.example.rpg.gameCharacter;

import jakarta.persistence.Entity;

@Entity
public class Priest extends GameCharacter {

    public Priest(String name) {
        super(name);
        this.health = 80;
        this.mana = 120;
    }

    protected Priest() {
    }
}
