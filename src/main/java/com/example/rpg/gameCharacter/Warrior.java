package com.example.rpg.gameCharacter;

import com.example.rpg.enums.ClassType;
import jakarta.persistence.Entity;

@Entity
public class Warrior extends GameCharacter {

    public Warrior(String name) {
        super(name);
        this.hp = 120;
        this.maxHp = 120;
        this.mana = 50;
        this.maxMana = 50;
        this.classType = ClassType.Warrior;
    }

    protected Warrior() {
    }
}
