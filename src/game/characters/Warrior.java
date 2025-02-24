package game.characters;

import game.mobs.Mob;

import java.util.Random;

public class Warrior extends GameCharacter {

    public Warrior(String name) {
        super(name, 130, 35, 25);
    }

    @Override
    public void attack(Mob enemy) {
        Random random = new Random();
        double multiplier = 0.9 + (1.1 - 0.9) * random.nextDouble();
        int damage = (int) (this.getAttackDamage() * multiplier);

        enemy.receiveDamage(damage);
    }
}
