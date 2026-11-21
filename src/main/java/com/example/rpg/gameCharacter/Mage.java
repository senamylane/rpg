package com.example.rpg.gameCharacter;

import com.example.rpg.enums.ClassType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@DiscriminatorValue("MAGE")
public class Mage extends Hero {
    public Mage(String name) {
        super(name);
        this.hp = 100;
        this.maxHp = 100;
        this.mana = 90;
        this.maxMana = 90;
        this.attack = 10;
        this.defense = 10;
        this.magic = 25;
        this.resistance = 25;
        this.dexterity = 18;
        this.classType = ClassType.Mage;
    }

    protected Mage() {
    }

    @Override
    public void attack(GameCharacter target) {
        float damage = 1.5f * this.magic - target.defense;
        if (damage <= 0) {
            return;
        }
        float variation = ThreadLocalRandom.current().nextFloat(0.8f, 1.201f);
        int finalDamage = (int) (damage * variation) + this.level;
        target.takeDamage(finalDamage);
    }

    public void fireTorrent(List<Enemy> enemies) {
        for (Enemy e : enemies) {
            float variation = ThreadLocalRandom.current().nextFloat(0.8f, 1.201f);
            int damage = this.magic - e.defense;
            int finalDamage = (int) (damage * variation);
            e.takeDamage(finalDamage);
        }
    }

    public void weakeningBolt(Enemy enemy) {
        enemy.attack = enemy.attack / 2;
    }
}
