import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayerCPU extends Player{
    private List<Slot> playerBoard;
    private Random rng = new Random();
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
        for (Slot slot : board.getPlayerBoard()) {
            if (slot.isEmpty()) {
                if (isSelfslotFree(slot)) {
                    place(getHand().get(getStrongestIndex()), board.getPlrSlot().indexOf(slot), false);
                }
            }
        }
        for (Slot slot : board.getPlayerBoard()) {
            if (slot.isEmpty()) {
                List<Integer> Ava = board.getIndexAvailableSlotList(false);
                int rand = rng.nextInt(Ava.size());
                place(getHand().get(getStrongestIndex()), rand, false);
            }

        }


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
            if (a<card.getDMG()) {
                a = card.getDMG();
            }
        }
        return a;
    }

}
