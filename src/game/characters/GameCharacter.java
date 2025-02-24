package game.characters;

import game.mobs.Mob;

public abstract class GameCharacter {
    private final String name;
    private int health;
    private int maxHealth;
    private int attackDamage;
    private int armor;
    private int experience = 0;
    private int maxExperience = 50;
    private int level = 1;
    private int goldAmount = 0;

    // Stats
    private static int easyMobsKilled = 0;
    private static int mediumMobsKilled = 0;
    private static int hardMobsKilled = 0;
    private static int bossKilled = 0;
    private static int totalGoldGained = 0;

    public GameCharacter(String name, int health, int attackDamage, int armor) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackDamage = attackDamage;
        this.armor = armor;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
    public void receiveDamage(int damage) {
        int reducedDamage = Math.max(damage - armor, 1);
        health -= reducedDamage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public abstract void attack(Mob enemy);

    public void gainExperience(int exp) {
        System.out.println("Gained " + exp + " experience points!");
        if (experience + exp >= maxExperience) {
            levelUp(exp);
        } else {
            experience += exp;
        }
    }

    public void levelUp(int exp) {
        level++;
        experience = experience + exp - maxExperience;
        maxExperience += 40;
        maxHealth += 15;
        health = maxHealth;
        attackDamage += 4;
        armor += 1;
        System.out.println("Leveled up to: " + level);
    }


    public void gainGold(int droppedGold) {
        goldAmount += droppedGold;
        System.out.println("Gained " + droppedGold + " gold!\n");
    }

    public void killMob(Mob mob) {
        System.out.println("\nYou defeated the target! :)");
        gainExperience(mob.dropExperience());

        int goldDropped = mob.dropGold();
        gainGold(goldDropped);
        totalGoldGained += goldDropped;

        switch (mob.getName()) {
            case "EasyMob":
                easyMobsKilled++;
                break;
            case "MediumMob":
                mediumMobsKilled++;
                break;
            case "HardMob":
                hardMobsKilled++;
                break;
            case "Boss":
                bossKilled++;
        }
    }

    public void displayAllStats() {
        System.out.printf("\n%-9s | HP: %4d/%4d | Attack: %3d | Armor: %3d | Level: %2d | EXP: %3d / %3d | GOLD: %d",
                name, health, maxHealth, attackDamage, armor, level, experience, maxExperience, goldAmount);
    }

    public void displayAfterdeathStats() {
        System.out.println("Easy mobs killed: " + easyMobsKilled);
        System.out.println("Medium mobs killed: " + mediumMobsKilled);
        System.out.println("Hard mobs killed: " + hardMobsKilled);
        System.out.println("Boss mobs killed: " + bossKilled);
        System.out.println("Total gold: " + totalGoldGained);
        System.out.println("Level: " + level);
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void purchaseItem(int healthBoost, int attackBoost, int armorBoost, int itemPrice) {
        this.maxHealth += healthBoost;
        this.health = (int) Math.min(maxHealth, this.health + this.maxHealth * 0.3);
        this.attackDamage += attackBoost;
        this.armor += armorBoost;
        this.goldAmount -= itemPrice;
    }
}
