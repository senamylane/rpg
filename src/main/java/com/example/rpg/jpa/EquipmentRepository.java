package com.example.rpg.jpa;

import com.example.rpg.equipment.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "demo", path = "equipment")
public abstract interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Long>, CrudRepository<Equipment, Long> {
    List<Equipment> findByNameIgnoreCase(String name);

    Equipment findByName(String name);

    @Query("SELECT a FROM Weapon a WHERE a.id = :id")
    Weapon findWeaponById(long id);

    @Query("SELECT a FROM ChestArmor a WHERE a.id = :id")
    ChestArmor findChestplateById(long id);

    @Query("SELECT a FROM Boot a WHERE a.id = :id")
    Boot findBootById(long id);

    @Query("SELECT a FROM Gloves a WHERE a.id = :id")
    Gloves findGlovesById(long id);

}
