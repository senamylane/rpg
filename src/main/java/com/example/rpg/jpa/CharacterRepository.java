package com.example.rpg.jpa;

import com.example.rpg.gameCharacter.GameCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "demo", path = "character")
public interface CharacterRepository extends PagingAndSortingRepository<GameCharacter, Long>, CrudRepository<GameCharacter, Long> {

    List<GameCharacter> findByNameIgnoreCase(String name);

    Character findById(long id);

}
