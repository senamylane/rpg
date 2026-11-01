package com.example.rpg.dto;

import com.example.rpg.enums.EquipmentType;
import jakarta.validation.constraints.Min;
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

    @Min(value = 1, message = "minimumDamage must be at least 1")
    private int minimumDamage;

    public int getMinimumDamage() {
        return minimumDamage;
    }

    public void setMinimumDamage(int minimumDamage) {
        this.minimumDamage = minimumDamage;
    }

    @Min(value = 1, message = "minimumMagicDamage must be at least 1")
    private int minimumMagicDamage;

    public int getMinimumMagicDamage() {
        return minimumMagicDamage;
    }

    public void setMinimumMagicDamage(int minimumMagicDamage) {
        this.minimumMagicDamage = minimumMagicDamage;
    }

}
