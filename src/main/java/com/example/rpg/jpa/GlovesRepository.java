package com.example.rpg.jpa;

import com.example.rpg.equipment.Gloves;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "equipment", path = "gloves")
public interface GlovesRepository extends PagingAndSortingRepository<Gloves, Long>, CrudRepository<Gloves, Long> {

}
