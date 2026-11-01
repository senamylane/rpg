package com.example.rpg.gameCharacter;

import com.example.rpg.enums.ClassType;
import jakarta.persistence.Entity;

@Entity
public class Priest extends GameCharacter {

    public Priest(String name) {
        super(name);
        this.hp = 80;
        this.maxHp = 80;
        this.mana = 120;
        this.maxMana = 120;
        this.classType = ClassType.Priest;
    }

    protected Priest() {
    }
}
