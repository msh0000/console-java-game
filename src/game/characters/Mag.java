package game.characters;

import game.mobs.Mob;

import java.util.Random;

public class Mag extends GameCharacter {

    public Mag(String name) {
        super(name, 90, 55, 8);
    }

    @Override
    public void attack(Mob enemy) {
        Random random = new Random();
        double multiplier = 0.8 + (1.3 - 0.8) * random.nextDouble();
        int damage = (int) (this.getAttackDamage() * multiplier);

        enemy.receiveDamage(damage);

    }
}
