package game.mobs;

import game.characters.GameCharacter;

import java.util.Random;

public class Mob{
    private final String name;
    private int health;
    private int maxHealth;
    private int attackDmg;
    private int armor;

    private Double LowerRandomPercentage;
    private Double UpperRandomPercentage;
    private int experienceDrop;
    private int goldDrop;


    public Mob(String name,int health, int attackDmg, int armor, double LowerDMGPercentage,double UpperDMGPercentage, int experienceDrop, int goldDrop) {
        this.name=name;
        this.health = health;
        this.maxHealth = health;
        this.attackDmg = attackDmg;
        this.armor = armor;
        this.LowerRandomPercentage = LowerDMGPercentage;
        this.UpperRandomPercentage = UpperDMGPercentage;
        this.experienceDrop = experienceDrop;
        this.goldDrop = goldDrop;
    }

    public String getName() {
        return name;
    }
    public void receiveDamage(int damage) {
        int reducedDMG = Math.max(damage - (armor),1);
        health -= reducedDMG;
    }

    public boolean isAlive() {
        return health>0;
    }

    public void displayStats() {
        System.out.printf("%-9s | HP: %4d | Attack: %3d | Armor: %3d%n",
                name, maxHealth, attackDmg, armor);
    }



    public int getAttackDmg() {
        return attackDmg;
    }

    public void attack(GameCharacter enemy){
        Random random = new Random();
        double multiplier = LowerRandomPercentage + (UpperRandomPercentage - LowerRandomPercentage) * random.nextDouble();
        int damage = (int) (getAttackDmg() * multiplier);

        enemy.receiveDamage(damage);
    }

    public int dropExperience() {
        Random random = new Random();
        double multiplier = LowerRandomPercentage + (UpperRandomPercentage - LowerRandomPercentage) * random.nextDouble();
        return (int) (experienceDrop * multiplier);
    }
    public int dropGold() {
        Random random = new Random();
        double multiplier = LowerRandomPercentage + (UpperRandomPercentage - LowerRandomPercentage) * random.nextDouble();
        return (int) (goldDrop * multiplier);
    }


    public void upgradeMobAndRespawn() {
        maxHealth += 3;
        health = maxHealth;
        attackDmg += 2;
        armor += 1;
        experienceDrop += 3;
        goldDrop += 2;
        LowerRandomPercentage = Math.min(0.9, LowerRandomPercentage + 0.005);
        UpperRandomPercentage = Math.min(1.3, UpperRandomPercentage + 0.005);
    }
}
