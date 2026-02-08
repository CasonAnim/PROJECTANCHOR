public class Board {
    public static final int SLOT_SIZE = 4;

    private Slot[] plrSlot = new Slot[SLOT_SIZE];
    private Slot[] enemySlot = new Slot[SLOT_SIZE];
    public Board() {
        for (int i = 0 ; i < SLOT_SIZE; i++) {
            plrSlot[i] = new Slot();
            enemySlot[i] = new Slot();
        }
    }

    public void placeCard(InstanceCard card,int index, boolean isPlayer) {
        if (index<0||index>=SLOT_SIZE) {
            throw new RuntimeException("ไอ้ควาย มันมีแค่4ช่อง");
        } else {
            if (isPlayer) {
                plrSlot[index].setCard(card);
            } else {
                enemySlot[index].setCard(card);
            }
        }
    }

    public void showBoard() {
        System.out.println("======CPU Board======");
        for (Slot slot : enemySlot) {
            slot.contain();
            System.out.println();
        }
        System.out.println("======PLAYER Board======");
        for (Slot slot : plrSlot) {
            slot.contain();
            System.out.println();
        }
    }
}
