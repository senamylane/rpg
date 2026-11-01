package com.example.rpg.enums;

import com.example.rpg.equipment.*;

public enum EquipmentType {
    WEAPON((Class<? extends Equipment>) Weapon.class),
    CHEST_ARMOR(ChestArmor.class),
    BOOTS(Boot.class),
    GLOVES(Gloves.class);

    private final Class<? extends Equipment> targetClass;

    // Constructor to bind the class
    EquipmentType(Class<? extends Equipment> targetClass) {
        this.targetClass = targetClass;
    }

    public Class<? extends Equipment> getTargetClass() {
        return targetClass;
    }
}
