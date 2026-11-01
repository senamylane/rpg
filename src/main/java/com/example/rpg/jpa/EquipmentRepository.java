package com.example.rpg.jpa;

import com.example.rpg.equipment.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "demo", path = "equipment")
public abstract interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Long>, CrudRepository<Equipment, Long> {
    List<Equipment> findByNameIgnoreCase(String name);

    Equipment findByName(String name);

}
