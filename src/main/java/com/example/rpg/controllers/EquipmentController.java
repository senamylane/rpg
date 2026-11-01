package com.example.rpg.controllers;

import com.example.rpg.dto.CreateEquipmentRequest;
import com.example.rpg.enums.EquipmentType;
import com.example.rpg.equipment.*;
import com.example.rpg.jpa.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/equipment") // This means URL's start with /demo (after Application path)
public class EquipmentController {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private GlovesRepository glovesRepository;
    @Autowired
    private BootRepository bootRepository;
    @Autowired
    private ChestArmorRepository chestRepository;
    @Autowired
    private WeaponRepository weaponRepository;


    @PostMapping(path = "/create") // Map ONLY POST Requests
    @Transactional
    public @ResponseBody Equipment createEquipment(@Valid @RequestBody CreateEquipmentRequest request) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        return this.handleItem(request);
    }

    @PostMapping(path = "/update") // Map ONLY POST Requests
    @Transactional
    public @ResponseBody Equipment updateEquipment(@Valid @RequestBody CreateEquipmentRequest request) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        return this.handleItem(request);
    }

    public Equipment handleItem(CreateEquipmentRequest request) {
        EquipmentType type = request.getType();
        String name = request.getName();
        int requiredStrength = Math.max(request.getRequiredStrength(), 1);
        int requiredDexterity = Math.max(request.getAdditionalDexterity(), 1);
        int requiredIntelligence = Math.max(request.getRequiredIntelligence(), 1);
        int defense = Math.max(request.getDefense(), 1);

        switch (type) {
            case WEAPON -> {
                int minimumDamage = request.getMinimumDamage();
                int minimunMagicDamage = request.getMinimumMagicDamage();
                if (minimumDamage <= 0) {
                    throw new IllegalArgumentException("MinimumDamage is required and must be greater than 0 for weapons.");
                }
                if (minimunMagicDamage <= 0) {
                    throw new IllegalArgumentException("MinimumMagicDamage is required and must be greater than 0 for weapons.");
                }
                Weapon weapon = new Weapon(name, requiredStrength, requiredDexterity, requiredIntelligence, minimunMagicDamage, minimumDamage);
                return equipmentRepository.save(weapon);
            }
            case CHEST_ARMOR -> {
                int additionalConstitution = request.getAdditionalConstitution();

                ChestArmor armor = new ChestArmor(name, requiredStrength, requiredDexterity, requiredIntelligence, defense, additionalConstitution);
                return equipmentRepository.save(armor);
            }
            case BOOTS -> {
                int additionalDexterity = request.getRequiredDexterity();

                Boot armor = new Boot(name, requiredStrength, requiredDexterity, requiredIntelligence, defense, additionalDexterity);
                return equipmentRepository.save(armor);
            }
            case GLOVES -> {
                int additionalWisdom = request.getAdditionalWisdom();

                Gloves armor = new Gloves(name, requiredStrength, requiredDexterity, requiredIntelligence, defense, additionalWisdom);
                return equipmentRepository.save(armor);
            }
        }
        return null;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Equipment> getAllEquipments() {
        // This returns a JSON or XML with the users
        List<Equipment> allItems = new ArrayList<>();
        allItems.addAll((Collection<? extends Equipment>) weaponRepository.findAll());
        allItems.addAll((Collection<? extends Equipment>) bootRepository.findAll());
        allItems.addAll((Collection<? extends Equipment>) chestRepository.findAll());
        allItems.addAll((Collection<? extends Equipment>) glovesRepository.findAll());
        return allItems;
    }

    @GetMapping(path = "/find")
    public @ResponseBody List<Equipment> getEquipmentByName(@RequestParam String name) {
        // This returns a JSON or XML with the users
        return equipmentRepository.findByNameIgnoreCase(name);
    }
}
