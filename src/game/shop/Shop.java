package game.shop;

import game.characters.GameCharacter;

import java.util.ArrayList;

public class Shop {
    private final ArrayList<Item> shopItems;

    public Shop() {
        shopItems = new ArrayList<>();
        shopItems.add(new Item("Sword", "Increase your attack by 8 points", 50, 0, 8, 0));
        shopItems.add(new Item("Potion", "Increase your health by 10 points, and heals 30% MaxHP", 40, 10, 0, 0));
        shopItems.add(new Item("Shield", "Increase your armor by 7 points", 45, 0, 0, 7));
    }

    public ArrayList<Item> getItems() {
        return shopItems;
    }

    public void displayShopItems() {
        int itemIndex = 1;
        for (Item item : shopItems) {
            System.out.printf("Type: %-3d  | %-8s | %2d g | %s%n", itemIndex, item.getName(), item.getPrice(), item.getDescription());
            itemIndex++;
        }
        System.out.printf("Type: %-3d  | %-8s%n", itemIndex, "Leave");
        System.out.println("Choose an item to buy:");
    }

    public void purchaseItem(int itemID, GameCharacter player) {
        if (itemID < 1 || itemID > shopItems.size()) {
            System.out.println("Invalid selection! Please choose a valid item.");
            return;
        }

        Item item = shopItems.get(itemID - 1);
        int playerGold = player.getGoldAmount();
        int itemPrice = item.getPrice();

        if (playerGold < itemPrice) {
            System.out.println("You don't have enough gold to buy this item.");
        } else {
            player.purchaseItem(item.getHealth(), item.getAttack(), item.getArmor(), itemPrice);
            System.out.println("You bought " + item.getName() + "!");
        }
    }
}
