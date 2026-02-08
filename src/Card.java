public class Card {
    private String name;
    private int maxHP;
    private int AT;
    private int cost;

    public Card(String name, int maxHP, int AT, int cost) {
        this.name = name;
        this.maxHP = maxHP;
        this.AT = AT;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAT() {
        return AT;
    }

    public int getCost() {
        return cost;
    }

    public void display() {
        System.out.println("Name : " + getName());
        System.out.println("MAXHP : " + getMaxHP());
        System.out.println("ATTACK : " + getAT());
        System.out.println("Cost : " + getCost());
    }

}
