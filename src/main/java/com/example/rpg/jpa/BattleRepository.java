package com.example.rpg.jpa;

import com.example.rpg.enums.BattleStatus;
import com.example.rpg.gameElements.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BattleRepository extends JpaRepository<Battle, UUID> {

    // Finds the current active battle for a given character ID
    Optional<Battle> findByPlayerIdAndStatus(Long playerId, BattleStatus status);

    // Checks if a character is already engaged in combat
    boolean existsByPlayerIdAndStatus(Long playerId, BattleStatus status);

    // Retrieves all battles (past and present) associated with a character
    List<Battle> findByPlayerId(Long playerId);
}