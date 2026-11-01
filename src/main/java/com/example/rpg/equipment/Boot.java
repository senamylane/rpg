package com.example.rpg.equipment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;

@JsonPropertyOrder({"name", "defense", "additionalDexterity"})
@Entity
public class Boot extends Armor {
    protected int additionalDexterity;

    public Boot(String name, int requiredStrength, int requiredDexterity, int requiredIntelligence, int defense, int additionalDexterity) {
        super(name, requiredStrength, requiredDexterity, requiredIntelligence, defense);
        this.additionalDexterity = additionalDexterity;
    }

    protected Boot() {
        super("", 1, 1, 1, 1);
    }

    public int getAdditionalDexterity() {
        return additionalDexterity;
    }

    public void setAdditionalDexterity(int additionalDexterity) {
        this.additionalDexterity = additionalDexterity;
    }
}
