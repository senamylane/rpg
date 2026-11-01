package com.example.rpg.jpa;

import com.example.rpg.equipment.ChestArmor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "equipment", path = "chestArmor")
public interface ChestArmorRepository extends PagingAndSortingRepository<ChestArmor, Long>, CrudRepository<ChestArmor, Long> {

}
