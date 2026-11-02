package com.example.rpg.gameElements;

import com.example.rpg.enums.BattleStatus;
import com.example.rpg.enums.Turn;
import com.example.rpg.gameCharacter.GameCharacter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "active_battles")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "character_id")
    private GameCharacter player;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "battle_id")
    private List<Enemy> enemies = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BattleStatus status = BattleStatus.IN_PROGRESS;

    @Enumerated(EnumType.STRING)
    private Turn currentTurn = Turn.PLAYER;

    private int turnCount = 1;

    public Battle() {
    }

    public Battle(GameCharacter player, List<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GameCharacter getPlayer() {
        return player;
    }

    public void setPlayer(GameCharacter player) {
        this.player = player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public BattleStatus getStatus() {
        return status;
    }

    public void setStatus(BattleStatus status) {
        this.status = status;
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Turn currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public boolean isFinished() {
        return status != BattleStatus.IN_PROGRESS;
    }
}
