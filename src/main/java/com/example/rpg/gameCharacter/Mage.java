package com.example.rpg.gameCharacter;

import com.example.rpg.enums.ClassType;
import jakarta.persistence.Entity;

@Entity
public class Mage extends GameCharacter {
    public Mage(String name) {
        super(name);
        this.hp = 70;
        this.maxHp = 70;
        this.mana = 90;
        this.maxMana = 90;
        this.classType = ClassType.Mage;
    }

    protected Mage() {
    }
}
