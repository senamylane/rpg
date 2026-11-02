package com.example.rpg.controllers;

import com.example.rpg.dto.CreateEnemyRequest;
import com.example.rpg.enums.EnemyRank;
import com.example.rpg.enums.Zone;
import com.example.rpg.gameElements.Enemy;
import com.example.rpg.jpa.EnemyRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/enemy")
public class EnemyController {
    @Autowired
    private EnemyRepository enemyRepository;

    @PostMapping(path = "/create") // Map ONLY POST Requests
    @Transactional
    public @ResponseBody Enemy createEquipment(@Valid @RequestBody CreateEnemyRequest request) {
        String name = request.getName();
        int level = request.getLevel();
        EnemyRank rank = request.getRank();
        Zone zone = Optional.ofNullable(request.getSpawnZone()).orElse(Zone.FOREST);

        Enemy e = new Enemy(name, rank, level, zone);

        return enemyRepository.save(e);
    }

    @GetMapping(path = "/find")
    public @ResponseBody List<Enemy> getEnemyByName(@RequestParam String name) {
        // This returns a JSON or XML with the users
        return enemyRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Enemy> getAllEnemies() {
        // This returns a JSON or XML with the users
        return (List<Enemy>) enemyRepository.findAll();
    }
}
