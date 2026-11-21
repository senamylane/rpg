package com.example.rpg.gameCharacter;

import com.example.rpg.enums.Spell;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@MappedSuperclass
public abstract class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String name;
    protected int level = 1;
    protected int hp = 50;
    protected int maxHp = 50;
    protected int mana = 30;
    protected int maxMana = 30;
    protected int attack = 10;
    protected int defense = 10;
    protected int magic = 10;
    protected int resistance = 10;

    protected GameCharacter(String name) {
        this.name = name;
    }

    protected GameCharacter() {
    }

    @Override
    public String toString() {
        return "character{" +
                "Name='" + name + '\'' +
                ", health=" + hp +
                ", mana=" + mana +
                '}';
    }


    public int getAttack() {
        return attack;
    }

    public void setAttack(int damage) {
        this.attack = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public String getName() {
        return name;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.min(level, 100);
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
        this.hp = Math.min(this.hp, this.maxHp);
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMana(int mana) {
        this.mana = Math.max(mana, 0);
        this.mana = Math.min(this.mana, this.maxMana);
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void attack(GameCharacter target) {
        float damage = 1.5f * this.attack - target.defense;
        float variation = ThreadLocalRandom.current().nextFloat(0.8f, 1.201f);
        int finalDamage = (int) (damage * variation) + this.level;
        target.takeDamage(finalDamage);
    }


    public void useSpell(Spell spell, List<GameCharacter> target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    }

    public void takeDamage(float amount) {
        if (amount <= 0) {
            return;
        }
        int newHp = this.hp - (int) amount;
        this.setHp(newHp);
    }

    public void takeBreath() {
        this.heal(this, 30);
        this.healMana(this, 15);
    }

    protected void heal(GameCharacter target, int amount) {
        target.setHp(target.getHp() + amount);
    }

    protected void healMana(GameCharacter target, int amount) {
        target.setMana(target.getMaxMana() + amount);
    }

    public void focus() {
        this.setLevel(this.level + 1);
    }
}
