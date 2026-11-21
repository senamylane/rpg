package com.example.rpg.gameElements;

// src/main/java/com/example/rpg/dto/SpellDTO.java
public class SpellDTO {
    private String id; // Ex: "fireTorrent"
    private String name; // Ex: "Fire Torrent"
    private int manaCost;
    private int numTargets;

    public SpellDTO(String id, String name, int manaCost, int numTargets) {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.numTargets = numTargets;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getNumTargets() {
        return numTargets;
    }
}