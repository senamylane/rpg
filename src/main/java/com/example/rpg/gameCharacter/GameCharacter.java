package com.example.rpg.gameCharacter;

import com.example.rpg.enemy.Enemy;
import com.example.rpg.equipment.ChestArmor;
import com.example.rpg.equipment.Equipment;
import com.example.rpg.equipment.Weapon;
import jakarta.persistence.*;

import java.util.List;

@Entity
public abstract class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected int health;
    protected int maxHealth;
    protected int mana;
    protected int maxMana;
    protected int force;
    protected int dexterity;
    protected int intelligence;
    protected int constitution;
    protected int wisdom;
    protected int charisma;
    protected String name;
    protected String charClass;
    protected List<Equipment> inventory;
    @ManyToOne
    protected Weapon weapon;
    @ManyToOne
    protected ChestArmor chestArmor;


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

    public void attack(Enemy enemy) {

    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public String getName() {
        return name;
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

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
