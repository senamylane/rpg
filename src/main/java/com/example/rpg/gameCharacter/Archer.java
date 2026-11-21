package com.example.rpg.gameCharacter;

import com.example.rpg.enums.ClassType;
import com.example.rpg.enums.Spell;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@DiscriminatorValue("ARCHER")
public class Archer extends Hero {
    public Archer(String name) {
        super(name);
        this.hp = 150;
        this.maxHp = 150;
        this.mana = 90;
        this.maxMana = 90;
        this.attack = 25;
        this.defense = 15;
        this.magic = 10;
        this.resistance = 10;
        this.dexterity = 25;
        this.classType = ClassType.Archer;
    }

    protected Archer() {
    }

    @Override
    public void attack(GameCharacter target) {
        float damage = (1.5f * this.attack - target.defense);
        float variation = ThreadLocalRandom.current().nextFloat(0.8f, 1.201f);
        int finalDamage = (int) (damage * variation) + this.level + (int) (0.5f * this.dexterity);
        target.takeDamage(finalDamage);
    }

    public void magicArrow(GameCharacter target) {
        float damage = 1.5f * this.attack + 0.5f * this.dexterity - target.defense;
        float variation = ThreadLocalRandom.current().nextFloat(0.8f, 1.201f);
        int finalDamage = (int) (damage * variation);
        target.takeDamage(finalDamage);
    }

    @Override
    public void useSpell(Spell spell, List<GameCharacter> target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = this.getClass().getDeclaredMethod(spell.name(), GameCharacter.class);
        for (GameCharacter character : target) {
            method.invoke(this, character);
        }
    }
}
