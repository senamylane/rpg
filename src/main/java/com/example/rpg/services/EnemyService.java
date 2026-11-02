package com.example.rpg.services;

import com.example.rpg.enums.EnemyRank;
import com.example.rpg.gameElements.Enemy;
import com.example.rpg.jpa.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnemyService {

    @Autowired
    private EnemyRepository enemyRepository;

    public Enemy createGoblinMinion() {
        Enemy goblin = new Enemy("Goblin Scout", EnemyRank.MINION, 50);
        return enemyRepository.save(goblin);
    }

    public Enemy createDragonBoss() {
        Enemy dragon = new Enemy("Elder Dragon", EnemyRank.BOSS, 5000);
        return enemyRepository.save(dragon);
    }
}