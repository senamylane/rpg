package com.example.rpg.jpa;

import com.example.rpg.equipment.Weapon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "equipment", path = "weapon")
public interface WeaponRepository extends PagingAndSortingRepository<Weapon, Long>, CrudRepository<Weapon, Long> {
    List<Weapon> findByNameIgnoreCase(String name);

    Weapon findById(long id);
}
