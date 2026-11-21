package com.example.rpg.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class CharacterSpellRequest {
    @NotNull
    @NotEmpty
    private List<UUID> targets;

    @NotNull
    private Long character_id;

    @NotNull
    private UUID battle_id;

    @NotNull
    private String spell;

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public UUID getBattle_id() {
        return battle_id;
    }

    public void setBattle_id(UUID battle_id) {
        this.battle_id = battle_id;
    }

    public List<UUID> getTargets() {
        return targets;
    }

    public void setTargets(List<UUID> targets) {
        this.targets = targets;
    }

    public Long getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(Long character_id) {
        this.character_id = character_id;
    }
}
