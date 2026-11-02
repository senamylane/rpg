package com.example.rpg.gameElements;

import com.example.rpg.enums.EnemyRank;
import com.example.rpg.enums.Zone;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.UUID;

@JsonPropertyOrder({"id", "name", "level", "rank", "hp", "maxHp", "mana", "maxMana", "defense", "expGiven"})
@Entity
public class Enemy implements Combatant {
    protected String name;
    protected int level = 1;
    protected int hp = 20;
    protected int maxHp = 20;
    protected int mana = 10;
    protected int maxMana = 10;
    protected int damage = 10;
    protected int defense = 10;
    protected int expGiven = 10;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected EnemyRank rank;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Zone spawnZone = Zone.FOREST;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID enemy_id;

    public Enemy(String name, EnemyRank rank, int level) {
        this.name = name;
        this.level = level;
        this.rank = rank;
        this.spawnZone = Zone.FOREST;
        this.setBasicStats();
    }

    public Enemy(String name, EnemyRank rank, int level, Zone spawnZone) {
        this.name = name;
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
        this.damage = this.level * 5 + multiplier * 20;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
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

    @Override
    public void attack(Combatant target) {
        target.takeDamage(3);
    }

    @Override
    public void useSpell(Combatant target) {

    }

    @Override
    public void takeDamage(int amount) {
        this.hp -= amount;
    }

    public Enemy copy(UUID id) {
        Enemy e = new Enemy();
        e.enemy_id = id;
        e.name = this.name;
        e.level = this.level;
        e.spawnZone = this.spawnZone;
        e.damage = this.damage;
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
