package com.example.rpg.gameCharacter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected int health;
    protected int mana;
    protected int force;
    protected int dexterity;
    protected int intelligence;
    protected int constitution;
    protected int wisdom;
    protected int charisma;
    protected String name;
    protected String charClass;

    public GameCharacter(String name) {
        this.name = name;
        this.charClass = getClass().getSimpleName();
    }

    protected GameCharacter() {
    }

    @Override
    public String toString() {
        return "character{" +
                "Name='" + name + '\'' +
                ", health=" + health +
                ", mana=" + mana +
                ", class=" + charClass +
                '}';
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    protected void setMana(int mana) {
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getCharClass() {
        return charClass;
    }

    protected void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
}
