package com.example.rpg.dto;

import com.example.rpg.enums.EnemyRank;
import com.example.rpg.enums.Zone;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateEnemyRequest {
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters")
    private String name;
    @Min(value = 1, message = "Level must be at least 1")
    private int level;
    @NotNull(message = "Rank cannot be empty")
    private EnemyRank rank;
    private Zone spawnZone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public EnemyRank getRank() {
        return rank;
    }

    public void setRank(EnemyRank rank) {
        this.rank = rank;
    }

    public Zone getSpawnZone() {
        return spawnZone;
    }

    public void setSpawnZone(Zone spawnZone) {
        this.spawnZone = spawnZone;
    }
}
