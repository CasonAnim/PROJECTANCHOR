public class InstanceCard {
    private Card card;
    private int selfHP;

    public InstanceCard(Card card ) {
        this.card = card;
        this.selfHP = card.getMaxHP();
    }

    public void TakeDamage(int dmg) {
        if (dmg <= selfHP) {
            selfHP -= dmg;
        } else {
            selfHP = 0;
        }
    }

    public boolean isDead() {
        return selfHP <= 0;
    }

    public void display() {
        System.out.println("Name : " + card.getName());
        System.out.println("MAXHP : " + card.getMaxHP());
        System.out.println("HP : " + this.selfHP);
        System.out.println("ATTACK : " + card.getAT());
        System.out.println("Cost : " + card.getCost());
    }
}
