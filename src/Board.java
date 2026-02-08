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

    public void play(boolean isPlaySide) {
        if (isPlaySide) {
            for (int i = 0; i < SLOT_SIZE; i++) {
                plrSlot[i].getCard().Attack(enemySlot[i]);
            }
        } else {
            for (int i = 0; i < SLOT_SIZE; i++) {
                enemySlot[i].getCard().Attack(plrSlot[i]);
            }
        }
        for (int i = 0; i < SLOT_SIZE; i++) {
            if (!enemySlot[i].isEmpty() || !plrSlot[i].isEmpty()) {
                if (enemySlot[i].getCard().isDead()) {
                    enemySlot[i] = new Slot();
                }
                if (plrSlot[i].getCard().isDead()) {
                    plrSlot[i] = new Slot();
                }
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

    public void showBoard(int index , boolean isPlayerSide) {
        if (index<0||index>=SLOT_SIZE) {
            throw new RuntimeException("ไอ้ควาย มันมีแค่4ช่อง");
        } else {
            if (isPlayerSide) {
                System.out.println("Player Side Slot ["+index+"] : " + plrSlot[index].isEmpty());
            } else {

                System.out.println("Player Side Slot ["+index+"] : " + enemySlot[index].isEmpty());
            }
        }
    }
}
