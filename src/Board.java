import java.util.*;

public class Board {
    public static final int SLOT_SIZE = 4;
    private List<Slot> temp;
    private List<Slot> tempOppo;
    private int DirectDMG =0;

    private List<Slot> plrSlot = new ArrayList<>();
    private List<Slot> enemySlot = new ArrayList<>();
    public Board() {
        for (int i = 0 ; i < SLOT_SIZE; i++) {
            plrSlot.add(new Slot());
            enemySlot.add(new Slot());
        }
    }



    public void placeCard(InstanceCard card, int index, boolean isPlayer) {
        Checker(index);
        if (isPlayer) {
            plrSlot.get(index).setCard(card);
        } else {
            enemySlot.get(index).setCard(card);
        }
    }
    public Slot getSlot(boolean isPlyaerside, int index) {
        Checker(index);

        if (isPlyaerside) {
            return plrSlot.get(index);
        } else {
            return enemySlot.get(index);
        }
    }

    public List<Slot> getPlayerBoard() {
        return List.copyOf(plrSlot);
    }

    public void play(boolean isPlayerSide) {
        setSideTempForBattle(isPlayerSide);

        for (Slot slot : temp) {
            if (!slot.isEmpty()) {
                if (!(tempOppo.get(temp.indexOf(slot)).isEmpty())) {
                    slot.getCard().Attack(tempOppo.get(temp.indexOf(slot)));
                    if (isDeadorEmpty(tempOppo.get(temp.indexOf(slot)))) {
                        tempOppo.set(temp.indexOf(slot), new Slot());
                    }
                }else {
                    DirectDMG += slot.getCard().getDMG();
                }
            }
        }
        updateSlot(isPlayerSide);

    }

    public void updateDeadstate() {
        for (Slot slot : plrSlot) {
            if (isDeadorEmpty(slot)) {
                plrSlot.set(plrSlot.indexOf(slot), new Slot());
            }
        }
        for (Slot slot : enemySlot) {
            if (isDeadorEmpty(slot)) {
                enemySlot.set(enemySlot.indexOf(slot), new Slot());
            }
        }
    }
    public List<Slot> getPlrSlot() {
        return plrSlot;
    }

    public void setPlrSlot(List<Slot> plrSlot) {
        this.plrSlot = plrSlot;
    }

    public List<Slot> getEnemySlot() {
        return enemySlot;
    }

    public void setEnemySlot(List<Slot> enemySlot) {
        this.enemySlot = enemySlot;
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

    public List<Integer> getIndexAvailableSlotList(boolean isPlayerSide) {
        SetSideForLoop(isPlayerSide);
        List<Integer> index = new ArrayList<>();
        for (Slot slot : temp) {
            index.add(temp.indexOf(slot));
        }
        clearState();
        return index;
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
        Checker(index);
        if (isPlayerSide) {
            System.out.println("Player Side Slot ["+index+"] : " + plrSlot.get(index));
        } else {

            System.out.println("CPU Side Slot ["+index+"] : " + enemySlot.get(index));
        }
    }

    public void Checker(int n) {
       if (n<0||n>=SLOT_SIZE) {
           throw new RuntimeException("ไอ้ควาย มันมีแค่4ช่อง");
       }
    }

    public void printAvailableSlot(boolean isPlayerSide) {
        SetSideForLoop(isPlayerSide);
        System.out.print("Available" + "\t");
        for (Slot slot : temp) {
            if (slot.isEmpty()) {
                System.out.print("[" +temp.indexOf(slot) + "]  ");
            }
        }
        System.out.println();
        clearState();
    }

    public void updateSlot (boolean isPlayerSide) {
        if (isPlayerSide) {
            plrSlot = temp;
            enemySlot = tempOppo;
        } else {
            plrSlot = tempOppo;
            enemySlot = temp;
        }
        clearState();
    }

    private void clearState() {
        temp = null;
        tempOppo = null;
    }

    public void clearDMG() {
        DirectDMG = 0;
    }

    public int getDirectDMG() {
        return DirectDMG;
    }

    private  void setSideTempForBattle(boolean isPlayerSide) {
        if (isPlayerSide) {
            temp = plrSlot;
            tempOppo = enemySlot;
        } else {
            temp = enemySlot;
            tempOppo = plrSlot;
        }
    }

    private void SetSideForLoop(boolean isPlayerSide) {
        if (isPlayerSide) {
            temp = plrSlot;
        } else {
            temp = enemySlot;
        }
    }
}
