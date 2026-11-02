package com.example.rpg.equipment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;

@JsonPropertyOrder({"id", "name", "defense", "additionalConstitution"})
@Entity
public class ChestArmor extends Armor {
    protected int additionalConstitution;

    public ChestArmor(String name, int requiredStrength, int requiredDexterity, int requiredIntelligence, int defense, int additionalConstitution) {
        super(name, requiredStrength, requiredDexterity, requiredIntelligence, defense);
        this.additionalConstitution = additionalConstitution;
    }

    protected ChestArmor() {
        super("", 1, 1, 1, 1);
    }

    public int getAdditionalConstitution() {
        return additionalConstitution;
    }

    public void setAdditionalConstitution(int additionalConstitution) {
        this.additionalConstitution = additionalConstitution;
    }
}
