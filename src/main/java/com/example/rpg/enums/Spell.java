package com.example.rpg.enums;

public enum Spell {
    fireTorrent(ClassType.Mage, "Fire Torrent", 15, 3),
    weakeningBolt(ClassType.Mage, "Weakening Bolt", 25, 2),
    magicArrow(ClassType.Archer, "Magic Arrow", 20, 1);

    private final String name;
    private final int manaCost;
    private final int numTargets;
    private final ClassType classType;

    Spell(ClassType classType, String name, int manaCost, int numTargets) {
        this.name = name;
        this.manaCost = manaCost;
        this.numTargets = numTargets;
        this.classType = classType;
    }

    @Override
    public String toString() {
        return "" + Spell.valueOf(this.getName());
    }

    public ClassType getClassType() {
        return classType;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getNumTargets() {
        return numTargets;
    }
}