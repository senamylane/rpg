package com.example.rpg.jpa;

import com.example.rpg.enums.Zone;
import com.example.rpg.gameElements.Enemy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "demo", path = "enemy", exported = false)
public interface EnemyRepository extends PagingAndSortingRepository<Enemy, Long>, CrudRepository<Enemy, Long> {

    List<Enemy> findByNameContainingIgnoreCase(String name);

    Enemy findByName(String name);

    List<Enemy> findBySpawnZone(Zone spawnZone);

}
