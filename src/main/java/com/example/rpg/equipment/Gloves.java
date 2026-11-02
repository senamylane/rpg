package com.example.rpg.equipment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;

@JsonPropertyOrder({"id", "name", "defense", "additionalWisdom"})
@Entity
public class Gloves extends Armor {
    protected int additionalWisdom;

    protected Gloves() {
        super("", 1, 1, 1, 1);
    }

    public Gloves(String name, int requiredStrength, int requiredDexterity, int requiredIntelligence, int defense, int additionalWisdom) {
        super(name, requiredStrength, requiredDexterity, requiredIntelligence, defense);
        this.additionalWisdom = additionalWisdom;
    }

    public int getAdditionalWisdom() {
        return additionalWisdom;
    }

    public void setAdditionalWisdom(int additionalWisdom) {
        this.additionalWisdom = additionalWisdom;
    }
}
