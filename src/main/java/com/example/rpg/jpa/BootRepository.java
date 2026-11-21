package com.example.rpg.jpa;

import com.example.rpg.equipment.Boot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "equipment", path = "boot")
public interface BootRepository extends PagingAndSortingRepository<Boot, Long>, CrudRepository<Boot, Long> {

}
