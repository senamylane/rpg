package com.example.rpg.jpa;

import com.example.rpg.gameCharacter.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "demo", path = "character")
public interface CharacterRepository extends PagingAndSortingRepository<Hero, Long>, CrudRepository<Hero, Long> {

    List<Hero> findByNameIgnoreCase(String name);

    Hero findById(long id);

}
