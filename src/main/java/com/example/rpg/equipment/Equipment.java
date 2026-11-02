package com.example.rpg.equipment;

import com.example.rpg.enums.EquipmentType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String name;
    protected EquipmentType type;
    protected String description;
    protected String imagePath;
    protected int requiredStrength = 1;
    protected int requiredDexterity = 1;
    protected int requiredIntelligence = 1;

    public Equipment(String name, int requiredStrength, int requiredDexterity, int requiredIntelligence) {
        this.name = name;
        this.requiredStrength = requiredStrength;
        this.requiredDexterity = requiredDexterity;
        this.requiredIntelligence = requiredIntelligence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getRequiredStrength() {
        return requiredStrength;
    }

    public void setRequiredStrength(int requiredStrength) {
        this.requiredStrength = requiredStrength;
    }

    public int getRequiredDexterity() {
        return requiredDexterity;
    }

    public void setRequiredDexterity(int requiredDexterity) {
        this.requiredDexterity = requiredDexterity;
    }

    public int getRequiredIntelligence() {
        return requiredIntelligence;
    }

    public void setRequiredIntelligence(int requiredIntelligence) {
        this.requiredIntelligence = requiredIntelligence;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
