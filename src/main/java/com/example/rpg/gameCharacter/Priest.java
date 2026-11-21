package com.example.rpg.gameCharacter;

import com.example.rpg.enums.ClassType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@DiscriminatorValue("PRIEST")
public class Priest extends Hero {

    public Priest(String name) {
        super(name);
        this.hp = 80;
        this.hp = 180;
        this.maxHp = 180;
        this.mana = 90;
        this.maxMana = 90;
        this.attack = 20;
        this.defense = 10;
        this.magic = 20;
        this.resistance = 20;
        this.dexterity = 8;
        this.classType = ClassType.Priest;
    }

    protected Priest() {
        super();
    }

    public void divineHealing() {
        int healAmount = (int) (this.maxHp * 0.25f) + this.magic;
        this.heal(this, healAmount);
    }

    public void divineLight(List<Enemy> enemies) {
        for (Enemy e : enemies) {
            float variation = ThreadLocalRandom.current().nextFloat(0.8f, 1.201f);
            int damage = this.magic - e.defense;
            int finalDamage = (int) (damage * variation);
            e.takeDamage(finalDamage);
        }
        this.heal(this, (int) (this.maxHp * 0.1f));
    }
}
