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
//        Startdraw(getDeck(), START_NUM);
        for (InstanceCard card : getDeck()) {
            add(card);
            System.out.println("BOT - "+ card.getName());
        }
//        for (InstanceCard card : getHand()) {
//            System.out.println("BOT - "+ card.getName());
//        }
    }
    public void placeLog(InstanceCard card, int indexSlot) {
        System.err.println("[BOT] - I place " + card.getName() + " at " + indexSlot);
    }
    @Override
    void playTurn() {


        if (getHand().size()<=5) {
            add(new InstanceCard(CardOriginal.CGUARD));
            add(new InstanceCard(CardOriginal.CGUARD));

        }
        if (BattleManager.ENEMYHP < BattleManager.MAXENEMYHP/2) {
           for (int i : board.getIndexAvailableSlotList(false)) {
               System.err.println("["+ i + "] - กลัวแล้วครับพี่");
               Slot eSlot = board.getEnemySlot().get(i);
               Slot pSlot = board.getPlrSlot().get(i);
               int index = getStrongestIndex(board.getPlrSlot());
               System.err .println("Strongest Enim is at " + index);
               if (eSlot.isEmpty()) {
                   System.err.println("[" + i + "] - Im ready");

                   if (eSlot.isEmpty()) {
                       InstanceCard mystrongest = getHand().get(getStrongestIndex());
                       InstanceCard myChokiest = getHand().get(getHighestHPIndex());
                       int plrStrongDMG = board.getPlrSlot().get(index).getCard().getDMG();
                       System.err.println("Mine : " + mystrongest.getDMG() + " DMG");
                       System.err.println("PLayer : " + plrStrongDMG + " DMG");
                       if (mystrongest.getDMG() >= plrStrongDMG) {
                           System.err.println("Fight");
                           System.err.println("Mine : " + mystrongest.getDMG() + " DMG");
                           System.err.println("PLayer : " + plrStrongDMG + " DMG");

                           for (int freeIndex : board.getIndexAvailableSlotList(true)){
                               if (board.getEnemySlot().get(freeIndex).isEmpty()) {
                                   System.err.print("I can play Here : ["+freeIndex+"] ");
                                   place(mystrongest, freeIndex, false);
                                   break;
                               } else {
                                   int indexRan = rng.nextInt(board.getIndexAvailableSlotList(false).size());
                                   System.err.println("I'll play here instead : " + indexRan);
                                   place(mystrongest, indexRan, false);
                                   break;
                               }

                           }
                           break;
                       } else {
                           System.err.println("Flight");
                           System.err.println("Mine : " + myChokiest.getDMG() + " DMG");
                           System.err.println("PLayer : " + plrStrongDMG + " DMG");
                           if (board.getEnemySlot().get(index).isEmpty()) {
                               place(myChokiest, index, false);
                               System.err.println("Guard");
                               break;
                           } else {
                               int indexRan = rng.nextInt(board.getIndexUnavailableSlotList(true).size());
                               if (board.getEnemySlot().get(indexRan).isEmpty()) {
                                   place(myChokiest, indexRan, false);
                                   System.err.println("Guard 2");
                                   break;
                               } else {
                                   int notgivashit = rng.nextInt(board.getIndexAvailableSlotList(false).size());
                                   System.err.println("Fuck");
                                   place(mystrongest,notgivashit,false);
                                   break;

                               }
                           }
                       }
                   }
               }

           }
        } else {
            for (int i : board.getIndexAvailableSlotList(false)) {
                Slot eSlot = board.getEnemySlot().get(i);
                Slot pSlot = board.getPlrSlot().get(i);
                if (eSlot.isEmpty()) {
                    System.err.println("[" + i + "] - Im ready");
                    if (pSlot.isEmpty()) {
                        System.err.println("[" + i + "] - Opposite me is empty");
                        placeLog(getHand().get(getStrongestIndex()), i);
                        place(getHand().get(getStrongestIndex()), i , false);
                        break;
                    } else {

                        System.err.println("[" + i + "] - Opposite me is not empty");
                        System.err.println("[BOT] - I won't do anything here");
                    }
                } else {
                    System.err.println("[" + i + "] - Im not ready");
                    System.err.println("[BOT] - I won't do anything here");
                }
            }

            System.err.println("CON 2");
        }
        tempdmg = 0;
        tempSlot.clear();
        GlobalListenerManger.getInstance().fireBotEndTurn();
    }


    private int getHighesHPIndex(List<Slot> list) {
        int a = 0;
        int index = 0;
        for (Slot slot : list) {
            if (!slot.isEmpty()) {
                if (a<slot.getCard().getHP()) {
                    a = slot.getCard().getHP();
                    index = list.indexOf(slot);
                }
            }

        }
        return index;
    }

    private int getHighestHPIndex() {
        int a = 0;
        int index = 0;
        for (InstanceCard slot : getHand()) {

            if (a<slot.getHP()) {
                a = slot.getHP();
                index = getHand().indexOf(slot);
            }


        }
        return index;
    }


    private int getLowestHPIndex(List<Slot> list) {
        int a = 0;
        int index = 0;
        for (Slot slot : list) {
            if (!slot.isEmpty()) {
                if (a>slot.getCard().getHP()) {
                    a = slot.getCard().getHP();
                    index = list.indexOf(slot);
                }
            }

        }
        return index;
    }

    private int getLowestHPIndex() {
        int a = 0;
        int index = 0;
        for (InstanceCard slot : getHand()) {

                if (a>slot.getHP()) {
                    a = slot.getHP();
                    index = getHand().indexOf(slot);
                }


        }
        return index;
    }

    private int getLowestAttackIndex(List<Slot> list) {
        int a = 0;
        int index = 0;
        for (Slot slot : list) {
            if (!slot.isEmpty()) {
                if (a>slot.getCard().getDMG()) {
                    a = slot.getCard().getDMG();
                    index = list.indexOf(slot);
                }
            }

        }
        return index;
    }

    private int getLowestAttackIndex() {
        int a = 0;
        int index = 0;
        for (InstanceCard slot : getHand()) {

            if (a>slot.getDMG()) {
                a = slot.getDMG();
                index = getHand().indexOf(slot);
            }


        }
        return index;
    }

    private int getStrongestIndex() {
        int a = 0;
        int index = 0;
        for (InstanceCard card : getHand()) {
            if (a<card.getDMG()) {
                a = card.getDMG();
                index = getHand().indexOf(card);
            }
        }
        return index;
    }
    private int getStrongestIndex(List<Slot> list) {
        int a = 0;
        int index = 0;
        for (Slot slot : list) {
            if (!slot.isEmpty()) {
                if (a<slot.getCard().getDMG()) {
                    a = slot.getCard().getDMG();
                    index = list.indexOf(slot);
                }
            }

        }
        return index;
    }


}
