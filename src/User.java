import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class User extends Player{
    private List<Integer> tempAva;
    private int selec;

    Scanner scanner = new Scanner(System.in);
    public User(Map<Card, Integer> deck ) {
        super(deck);
        Startdraw(getDeck(), START_NUM);

        GlobalListenerManger.getInstance().onDrawListener(() -> {
            draw();
        });
        GlobalListenerManger.getInstance().onSelectListener(e -> {
                setTempSelect(e);
        });

        GlobalListenerManger.getInstance().onOnplace(((index, isPlayer) -> {
            if (getTempSelect() != null) {
                place(getTempSelect(), index, isPlayer);
                clearSelect();
                show();
                showHand();
            } else {
                System.out.println("Select something");
            }
        }));
    }




    @Override
    void playTurn() {
        System.out.println("Choose What u gonna do");
        System.out.println("[1] - Draw");
        System.out.println("[2] - Place");
        Decidetion(scanner.nextInt());
    }


    @Override
    public void place(InstanceCard card, int index, boolean isPlayerSide) {
        super.place(card, index, isPlayerSide);
        GlobalListenerManger.getInstance().fireOnRemove(index);
    }

    private void Decidetion(int n) {
        switch (n) {
            case 1 :
                System.out.println("1");
                draw();
                show();
                break;
            case 2 :
                System.out.println("2");
                board.printAvailableSlot(true);
                System.out.println("Choose Slot to place : ");
                selec = scanner.nextInt();
                selectSlot(selec);
                break;
            case 3 :
                System.out.println("3");
                break;
        }
    }

    private void selectSlot(int index) {
        tempAva = board.getIndexAvailableSlotList(true);

        if (tempAva.contains(index)) {
            System.out.println("Choose card");
            showHand();
            selectCard(scanner.nextInt());
        }
        tempAva = null;
    }

    private void selectCard(int index) {
        place(getHand().get(index), selec , true);
    }


    @Override
    public void draw() {
        InstanceCard curCard = null;
        if (getDeck().size() == 1) {
            curCard = getDeck().getFirst();
            Deckremove(curCard);
            add(curCard);
            System.out.println("Deckout");
        } else if(getDeck().size()>=2) {
            curCard = getDeck().get(rng.nextInt(0,getDeck().size()));
            Deckremove(curCard);
            add(curCard);
        } else {
            System.out.println("DECKOUT");
        }

        if (curCard != null) {
            System.out.println(curCard.getName());
            GlobalListenerManger.getInstance().fireAddCard(curCard);
        }


        show();
    }

    @Override
    public void Startdraw(List<InstanceCard> deck, int n) {
        super.Startdraw(deck, n);
        for (InstanceCard card : getHand()) {
            GlobalListenerManger.getInstance().fireAddCard(card);
        }
    }


}
