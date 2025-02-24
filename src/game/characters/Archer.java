package game.characters;

import game.mobs.Mob;

import java.util.Random;

public class Archer extends GameCharacter {

    public Archer(String name) {
        super(name, 110, 45, 12);
    }

    @Override
    public void attack(Mob enemy) {
        Random random = new Random();
        double multiplier = 0.85 + (1.25 - 0.85) * random.nextDouble();
        int damage = (int) (this.getAttackDamage() * multiplier);

        enemy.receiveDamage(damage);

    }
}
