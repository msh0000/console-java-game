package game.shop;

public class Item {
    private final String name;
    private final String description;
    private final int price;
    private final int health;
    private final int attack;
    private final int armor;


    public Item(String name,String description, int price, int health, int attack, int armor) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack(){
        return attack;
    }

    public int getArmor() {
        return armor;
    }



}
