package com.example.rpg.dto;

import com.example.rpg.enums.EquipmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateEquipmentRequest {
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Type cannot be empty")
    private EquipmentType type;

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    private int requiredIntelligence;
    private int requiredStrength;
    private int requiredDexterity;
    private int defense;
    private int additionalConstitution;
    private int additionalWisdom;
    private int additionalDexterity;

    public int getRequiredIntelligence() {
        return requiredIntelligence;
    }

    public void setRequiredIntelligence(int requiredIntelligence) {
        this.requiredIntelligence = requiredIntelligence;
    }


    private int minimumDamage;

    public int getMinimumDamage() {
        return minimumDamage;
    }

    public void setMinimumDamage(int minimumDamage) {
        this.minimumDamage = minimumDamage;
    }

    private int minimumMagicDamage;

    public int getMinimumMagicDamage() {
        return minimumMagicDamage;
    }

    public void setMinimumMagicDamage(int minimumMagicDamage) {
        this.minimumMagicDamage = minimumMagicDamage;
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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAdditionalConstitution() {
        return additionalConstitution;
    }

    public void setAdditionalConstitution(int additionalConstitution) {
        this.additionalConstitution = additionalConstitution;
    }

    public int getAdditionalWisdom() {
        return additionalWisdom;
    }

    public void setAdditionalWisdom(int additionalWisdom) {
        this.additionalWisdom = additionalWisdom;
    }

    public int getAdditionalDexterity() {
        return additionalDexterity;
    }

    public void setAdditionalDexterity(int additionalDexterity) {
        this.additionalDexterity = additionalDexterity;
    }
}
