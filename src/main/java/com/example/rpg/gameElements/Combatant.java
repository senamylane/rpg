package com.example.rpg.gameElements;

import com.example.rpg.gameCharacter.GameCharacter;

public interface Combatant {
    void attack(GameCharacter target);

    void useSpell(GameCharacter target);

    void takeDamage(float amount);
}
