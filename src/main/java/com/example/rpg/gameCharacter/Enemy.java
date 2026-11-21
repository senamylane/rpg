package com.example.rpg.gameCharacter;

import com.example.rpg.enums.EnemyRank;
import com.example.rpg.enums.Zone;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

@JsonPropertyOrder({"id", "name", "level", "rank", "hp", "maxHp", "mana", "maxMana", "defense", "expGiven"})
@Entity
public class Enemy extends GameCharacter {

    protected int expGiven = 10;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected EnemyRank rank;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Zone spawnZone = Zone.FOREST;
    private UUID enemy_id;
    public static int numSlain = 0;

    public Enemy(String name, EnemyRank rank, int level) {
        super(name);
        this.level = level;
        this.rank = rank;
        this.setBasicStats();
    }

    public Enemy(String name, EnemyRank rank, int level, Zone spawnZone) {
        super(name);
        this.level = level;
        this.rank = rank;
        this.spawnZone = spawnZone;
        this.setBasicStats();
    }

    protected Enemy() {

    }

    private void setBasicStats() {
        int multiplier = this.getRankMultiplier(rank);
        this.hp = this.level * 10 + multiplier * 20;
        this.maxHp = this.hp;
        this.mana = this.level * 3 + multiplier * 7;
        this.maxMana = this.mana;
        this.attack = this.level * 5 + multiplier * 20;
        this.defense = this.level * 2 + multiplier * 15;
        this.expGiven = this.level * 2 + multiplier * 12;
    }

    private int getRankMultiplier(EnemyRank rank) {
        return switch (rank) {
            case MINION -> 1;
            case GENERAL -> 10;
            case SUPERIOR -> 18;
            case BOSS -> 25;
            case ULTIMATE_BOSS -> 50;
        };
    }

    @Override
    public void attack(GameCharacter target) {
        int damage = (int) (Enemy.numSlain * 0.1f + (1.5f * this.attack - target.defense));
        target.takeDamage(damage);
    }


    public int getExpGiven() {
        return expGiven;
    }

    public void setExpGiven(int expGiven) {
        this.expGiven = expGiven;
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

    public UUID getEnemy_id() {
        return enemy_id;
    }

    public void setEnemy_id(UUID enemy_id) {
        this.enemy_id = enemy_id;
    }

    public Enemy copy(UUID id) {
        Enemy e = new Enemy();
        e.enemy_id = id;
        e.name = this.name;
        e.level = this.level;
        e.spawnZone = this.spawnZone;
        e.attack = this.attack;
        e.defense = this.defense;
        e.mana = this.mana;
        e.maxMana = this.maxMana;
        e.hp = this.hp;
        e.maxHp = this.maxHp;
        e.expGiven = this.expGiven;
        e.rank = this.rank;
        return e;
    }
}
