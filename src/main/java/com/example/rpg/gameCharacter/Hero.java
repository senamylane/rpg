package com.example.rpg.gameCharacter;

import com.example.rpg.enums.ClassType;
import com.example.rpg.enums.EquipmentType;
import com.example.rpg.enums.Zone;
import com.example.rpg.equipment.ChestArmor;
import com.example.rpg.equipment.Weapon;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "heroes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "hero_class", discriminatorType = DiscriminatorType.STRING)
@JsonPropertyOrder(
        {"id", "name", "level", "hp", "maxHp", "mana", "maxMana", "attack", "defense", "force",
                "intelligence", "constitution", "dexterity", "wisdom", "charisma"
        })
public abstract class Hero extends GameCharacter {
    protected int force = 5;
    protected int intelligence = 5;
    protected int dexterity = 5;
    protected int constitution = 5;
    protected int wisdom = 5;
    protected int charisma = 5;
    protected ClassType classType;
    @ManyToOne
    protected Weapon weapon;
    @ManyToOne
    protected ChestArmor chestArmor;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Zone currentZone = Zone.CAMP_SITE;
    protected int experience = 0;
    protected int nextLevelExperience = 100;
    @ElementCollection
    @CollectionTable(
            name = "personagem_inventario",
            joinColumns = @JoinColumn(name = "personagem_id")
    )
    @MapKeyColumn(name = "equipment_id") // Creates the ID column (the Map Key)
    @Column(name = "equipment_type")     // Creates the Type column (the Map Value)
    @Enumerated(EnumType.STRING)
    private Map<Long, EquipmentType> inventory = new HashMap<>();

    public Hero(String name) {
        super(name);
    }

    protected Hero() {
    }

    public static Hero getClass(ClassType classType, String name) {
        return switch (classType) {
            case Mage -> new Mage(name);
            case Warrior -> new Warrior(name);
            case Priest -> new Priest(name);
            case Archer -> new Archer(name);
        };
    }

    public boolean addExperience(int exp) {
        this.experience += exp;

        if (this.experience >= this.nextLevelExperience) {
            this.level++;
            this.experience -= this.nextLevelExperience;
            return true;
        }
        return false;
    }

    public void revive() {
        this.hp = this.maxHp;
        this.mana = this.maxMana;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
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

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Map<Long, EquipmentType> inventory() {
        return inventory;
    }

    public void setInventory(Map<Long, EquipmentType> inventory) {
        this.inventory = inventory;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ChestArmor getChestArmor() {
        return chestArmor;
    }

    public void setChestArmor(ChestArmor chestArmor) {
        this.chestArmor = chestArmor;
    }

    public Zone getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(Zone currentZone) {
        this.currentZone = currentZone;
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
