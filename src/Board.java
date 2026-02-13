import java.util.ArrayList;
import java.util.List;

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
    public Slot getSlot(boolean isPlyaerside, int index) {
        if (isPlyaerside) {
            return plrSlot[index];
        } else {
            return enemySlot[index];
        }
    }

    public void play(boolean isPlaySide) {
        if (isPlaySide) {
            for (int i = 0; i < SLOT_SIZE; i++) {
                if (!plrSlot[i].isEmpty()) {
                    if (!enemySlot[i].isEmpty()) {
                        plrSlot[i].getCard().Attack(enemySlot[i]);
                        if (isDeadorEmpty(enemySlot[i])) {
                            enemySlot[i] = new Slot();
                        }
                    }
                }
            }

        } else {
            for (int i = 0; i < SLOT_SIZE; i++) {
                if (!enemySlot[i].isEmpty()) {
                    if (!plrSlot[i].isEmpty()) {
                        enemySlot[i].getCard().Attack(plrSlot[i]);
                        if (isDeadorEmpty(plrSlot[i])) {
                            plrSlot[i] = new Slot();
                        }
                    }
                }

            }
        }

    }

    public List<Slot> getListSlot(boolean isPlayerSide) {
        List<Slot> avslot = new ArrayList<>();
        if (isPlayerSide) {
            for (Slot slot : plrSlot) {
                if (slot.isEmpty()) {
                    avslot.add(slot);
                }
            }
        } else {
            for (Slot slot : enemySlot) {
                if (slot.isEmpty()) {
                    avslot.add(slot);
                }
            }
        }

        return avslot;
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

    public boolean isDeadorEmpty(Slot slot) {
        if (!slot.isEmpty()){
            if (slot.getCard().isDead()){
                return true;
            }else {
                return false;
            }
        } else {
            return true;
        }
    }

    public void showBoard(int index , boolean isPlayerSide) {
        if (index<0||index>=SLOT_SIZE) {
            throw new RuntimeException("ไอ้ควาย มันมีแค่4ช่อง");
        } else {
            if (isPlayerSide) {
                System.out.println("Player Side Slot ["+index+"] : " + plrSlot[index].isEmpty());
            } else {

                System.out.println("CPU Side Slot ["+index+"] : " + enemySlot[index].isEmpty());
            }
        }
    }
}
