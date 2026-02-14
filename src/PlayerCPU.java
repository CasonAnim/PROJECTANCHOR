import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayerCPU extends Player{
    private List<Slot> playerBoard;
    private Random rng = new Random();
    private List<Integer> tempSlot = new ArrayList<>();
    private int tempdmg =0;
    private int temp = 0;
    public PlayerCPU(Map<Card, Integer> deck) {
        super(deck);
        for (InstanceCard card : getDeck()) {
            add(card);
        }
        for (InstanceCard card : getHand()) {
            System.out.println("BOT - "+ card.getName());
        }
    }

    @Override
    void playTurn() {
        if (BattleManager.ENEMYHP < BattleManager.MAXENEMYHP/2) {
            for (Slot slot : board.getPlayerBoard()) {
                if (!slot.isEmpty()) {
                    tempdmg += slot.getCard().getDMG();
                    tempSlot.add(board.getPlayerBoard().indexOf(slot));
                }
            }
            if (tempdmg >= BattleManager.ENEMYHP) {
                for (int n : tempSlot) {
                    if (board.getEnemySlot().get(n).isEmpty()){
                        place(getHand().get(getWeakestIndex()), n,false);
                    }
                }
            }
        } else {
            for (Slot slot : board.getEnemySlot()) {
                if (slot.isEmpty() && board.getPlrSlot().get(board.getEnemySlot().indexOf(slot)).isEmpty()) {
                    place(getHand().get(getStrongestIndex()), board.getEnemySlot().indexOf(slot), false);
                    break;
                } else if (board.getEnemySlot().get(board.getEnemySlot().indexOf(slot)+1) != null && board.getEnemySlot().get(board.getEnemySlot().indexOf(slot)+1).isEmpty()){
                    place(getHand().get(getStrongestIndex()), board.getEnemySlot().indexOf(slot)+1, false);
                    break;
                } else {
                    place(getHand().get(getStrongestIndex()), board.getEnemySlot().indexOf(slot), false);
                    break;
                }
            }
        }
        tempdmg = 0;
        tempSlot.clear();
    }

    private int getLowestHPIndex() {
        int a = 0;
        for (Slot slot : board.getPlayerBoard()) {
            if (a<slot.getCard().getHP()) {
                a = slot.getCard().getHP();
            }
        }
        return a;
    }

    private boolean isSelfslotFree(Slot slot) {
        return board.getEnemySlot().get(board.getPlrSlot().indexOf(slot)).isEmpty();
    }

    private int getLowestAttackIndex() {
        int a = 0;
        for (Slot slot : board.getPlayerBoard()) {
            if (a<slot.getCard().getDMG()) {
                a = slot.getCard().getDMG();
            }
        }
        return a;
    }

    private int getStrongestIndex() {
        int a = 0;
        for (InstanceCard card : getHand()) {
            if (a>card.getDMG()) {
                a = card.getDMG();
            }
        }
        return a;
    }
    private int getWeakestIndex() {
        int a = 0;
        for (InstanceCard card : getHand()) {
            if (a<card.getDMG()) {
                a = card.getDMG();
            }
        }
        return a;
    }

}
