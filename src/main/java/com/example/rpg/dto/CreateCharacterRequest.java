package com.example.rpg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCharacterRequest {
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
