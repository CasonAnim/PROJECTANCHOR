public class Slot {

    private InstanceCard card;

    public Slot(InstanceCard card) {
        this.card = card;
    }

    public Slot() {
        this.card = null;
    }

    public boolean isEmpty() {
        return this.card==null;
    }

    public void contain() {
        if (isEmpty()) {
            System.out.println("SLOT EMPTY");
        } else {
            System.out.println("===========CONTAIN==========");
            card.display();
            System.out.println("============================");
        }
    }

    public void setCard(InstanceCard card) {
        this.card = card;
    }
    public void setCard(Card card) {
        this.card = new InstanceCard(card);
    }
}
