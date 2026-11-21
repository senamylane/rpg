package com.example.rpg.gameCharacter;

import com.example.rpg.enums.ClassType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("WARRIOR")
public class Warrior extends Hero {

    public Warrior(String name) {
        super(name);
        this.hp = 200;
        this.maxHp = 200;
        this.mana = 90;
        this.maxMana = 90;
        this.attack = 20;
        this.defense = 20;
        this.magic = 12;
        this.resistance = 12;
        this.dexterity = 13;
        this.classType = ClassType.Warrior;
    }

    protected Warrior() {
    }
}
