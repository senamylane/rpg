package com.example.rpg.gameCharacter;

import com.example.rpg.enemy.Enemy;
import com.example.rpg.enums.ClassType;
import com.example.rpg.equipment.ChestArmor;
import com.example.rpg.equipment.Weapon;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class GameCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected int level = 1;
    protected int hp = 50;
    protected int maxHp = 50;
    protected int mana = 30;
    protected int maxMana = 30;
    protected int force = 5;
    protected int dexterity = 5;
    protected int intelligence = 5;
    protected int constitution = 5;
    protected int wisdom = 5;
    protected int charisma = 5;
    protected int experience = 0;
    protected int nextLevelExperience = 100;
    protected String name;
    protected ClassType classType;
    @ElementCollection
    @CollectionTable(
            name = "personagem_inventario", // Nome da tabela que será criada
            joinColumns = @JoinColumn(name = "personagem_id") // FK apontando para o personagem
    )
    @Column(name = "equipment_id")
    protected List<Long> inventory;
    @ManyToOne
    protected Weapon weapon;
    @ManyToOne
    protected ChestArmor chestArmor;


    public GameCharacter(String name) {
        this.name = name;
        this.classType = ClassType.Normal;
    }

    protected GameCharacter() {
    }

    @Override
    public String toString() {
        return "character{" +
                "Name='" + name + '\'' +
                ", health=" + hp +
                ", mana=" + mana +
                ", class=" + classType +
                '}';
    }

    public void attack(Enemy enemy) {

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

    public ClassType getClassType() {
        return classType;
    }

    protected void setClassType(ClassType charClass) {
        this.classType = charClass;
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

    public int getMaxHp() {
        return maxHp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventory(List<Long> inventory) {
        this.inventory = inventory;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setChestArmor(ChestArmor chestArmor) {
        this.chestArmor = chestArmor;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getNextLevelExperience() {
        return nextLevelExperience;
    }

    public void setNextLevelExperience(int nextLevelExperience) {
        this.nextLevelExperience = nextLevelExperience;
    }
}
