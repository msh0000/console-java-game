package game.core;

import game.characters.GameCharacter;
import game.characters.Archer;
import game.characters.Mag;
import game.characters.Warrior;
import game.mobs.Mob;
import game.shop.Shop;

import java.util.Scanner;

public class Game {
    private final Shop shop;
    private GameCharacter player;
    private final Mob mobEasy;
    private final Mob mobMedium;
    private final Mob mobHard;
    private final Mob boss;
    private final Scanner sc;

    public Game() {
        shop = new Shop();
        mobEasy = new Mob("EasyMob", 150, 25, 10, 0.7, 1, 30, 15);
        mobMedium = new Mob("MediumMob", 180, 35, 15, 0.8, 1.1, 45, 25);
        mobHard = new Mob("HardMob", 220, 50, 25, 0.9, 1.2, 65, 40);
        boss = new Mob("Boss", 300, 80, 40, 1, 1.5, 100, 70);
        sc = new Scanner(System.in);

        System.out.println("What is your name?");
        String name = sc.nextLine();

        boolean repeatInput;
        do {
            repeatInput = false;
            System.out.printf("%-9s | HP: %3d | Attack: %3d | Armor: %3d \n", "Warrior", 150, 30, 20);
            System.out.printf("%-9s | HP: %3d | Attack: %3d | Armor: %3d \n", "Archer", 120, 40, 15);
            System.out.printf("%-9s | HP: %3d | Attack: %3d | Armor: %3d \n\n", "Mage", 100, 50, 10);
            System.out.println("Choose a class: 1-Warrior, 2-Archer, 3-Mage");

            if (sc.hasNextInt()) {
                int characterClass = sc.nextInt();
                sc.nextLine();
                switch (characterClass) {
                    case 1 -> player = new Warrior(name);
                    case 2 -> player = new Archer(name);
                    case 3 -> player = new Mag(name);
                    default -> {
                        repeatInput = true;
                        System.out.println("Invalid character class, please try again:");
                    }
                }
            } else {
                sc.next();
                repeatInput = true;
                System.out.println("Enter a valid number!");
            }
        } while (repeatInput);
    }


    public void mainGame() {
        boolean repeatInput;
        do {
            repeatInput = false;
            player.displayAllStats();
            System.out.println("\n1- Fight");
            System.out.println("2- Shop");
            System.out.println("3- Exit the game\n");
            System.out.println("What would you like to do?");

            if (sc.hasNextInt()) {
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1 -> fightsMenu();
                    case 2 -> shopMenu();
                    case 3 -> {
                        System.out.println("\n\n\n\n\nGame ended.\n\n\n");
                        player.displayAfterdeathStats();

                        sc.close();
                        System.exit(0);
                    }
                    default -> {
                        repeatInput = true;
                        System.out.println("Invalid option, please try again:");
                    }
                }
            } else {
                sc.next();
                repeatInput = true;
                System.out.println("Enter a valid number!");
            }
        } while (repeatInput);
    }

    public void fightsMenu() {
        boolean repeatInput = true;
        while (repeatInput && player.isAlive()) {

            player.displayAllStats();
            System.out.println("\n");
            mobEasy.displayStats();
            mobMedium.displayStats();
            mobHard.displayStats();
            boss.displayStats();
            System.out.println("Who do you want to fight: 1-EasyMob, 2-MediumMob, 3-HardMob, 4-Boss, 5-Exit");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> fight(mobEasy);
                    case 2 -> fight(mobMedium);
                    case 3 -> fight(mobHard);
                    case 4 -> fight(boss);
                    case 5 -> repeatInput = false;
                    default -> System.out.println("Invalid choice, please try again:");
                }
            } else {
                sc.next();
                System.out.println("Enter a valid number!");
            }

            if (!player.isAlive()) {
                System.out.println("\n\n\n\n\n===== You died =====\n\n\n");
                player.displayAfterdeathStats();
                sc.close();
                System.exit(0);
            }
        }
        mainGame();
    }

    public void shopMenu() {
        while (true) {
            player.displayAllStats();
            System.out.printf("You have: %-3d gold\n", player.getGoldAmount());
            shop.displayShopItems();
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice >= 1 && choice <= shop.getItems().size()) {
                    shop.purchaseItem(choice, this.player);
                } else if (choice == shop.getItems().size() + 1) {
                    break;
                } else {
                    System.out.println("Invalid choice, please try again.");
                }
            } else {
                sc.next();
                System.out.println("Enter a valid number!");
            }
        }
        mainGame();
    }

    public void fight(Mob mob) {
        while (player.isAlive()) {
            player.attack(mob);
            if (mob.isAlive()) {
                mob.attack(player);
            } else {
                player.killMob(mob);
                break;
            }
        }
        mob.upgradeMobAndRespawn();
    }
}
