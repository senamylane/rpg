package com.example.rpg.dto;

import com.example.rpg.enums.EquipmentType;
import jakarta.validation.constraints.NotNull;

public class PickUpEquipmentRequest {
    private Long character_id;
    private Long equipment_id;
    private EquipmentType type;

    @NotNull
    public Long getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(Long character_id) {
        this.character_id = character_id;
    }

    @NotNull
    public Long getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }

    @NotNull
    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }
}
