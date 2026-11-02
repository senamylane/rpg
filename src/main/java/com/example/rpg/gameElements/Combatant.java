package com.example.rpg.gameElements;

public interface Combatant {
    void attack(Combatant target);

    void useSpell(Combatant target);

    void takeDamage(int amount);
}
