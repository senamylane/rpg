package com.example.rpg.dto;

import com.example.rpg.enums.ClassType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private ClassType classType;

    public ClassType classType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }
}
