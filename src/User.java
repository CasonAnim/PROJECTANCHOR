import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class User extends Player{
    private List<Integer> tempAva;
    private int selec;
    private int indexPlaced = 0;
    private int cost = 0;
    private List<InstanceCard> current = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
    public User(Map<Card, Integer> deck ) {
        super(deck);


        GlobalListenerManger.getInstance().onSacrificeSelectListener(e-> {
                current.add(e);
                System.out.println("Budget" + current.size());

                if (cost == current.size()) {
                    SacPlace();
                } else {
                    fireSacrificeable();
                }
        });

        GlobalListenerManger.getInstance().OnRemoteEvent(((Channel, data) -> {
            System.out.println("DEBUG: Event received on Channel " + Channel);
            if (Channel == 0 ) {
                System.err.println("Receive Request");
                GlobalListenerManger.getInstance().FireRemoteEvent(1, getDeckTemplate());
                System.err.println("[USER] - SENDINDECK TO SAVE || DECK SIZE : " + getDeckTemplate().size());
            }
            if (Channel == 4 ) {
                System.err.println("[CHANEL 4] - CONNECT -> ADDING CARD");
                if (data instanceof Card) {
                   addTodeck( (Card) data);
                }
            }
        }));

        GlobalListenerManger.getInstance().onUiListener(e -> {
            if (e==19) {
//                System.out.println(getDeckTemplate());
            }
        });

//        GlobalListenerManger.getInstance().OnRemoteEvent((Channel, data) -> {
//                    if (Channel==0) {
//                        if (data == null) {
//
//                            GlobalListenerManger.getInstance().FireRemoteEvent(1, getDeckTemplate());
//                            System.err.println("[USER] - SENDINDECK TO SAVE || DECK SIZE : " + getDeckTemplate().size());
//                        }
//                    } else if (Channel==4) {
//                        if (data instanceof Card) {
//                            addTodeck((Card) data);
//                        }
//                    }
//                }
//        );

        GlobalListenerManger.getInstance().onDrawListener(e -> {
            if (e) {
                add(new InstanceCard(CardOriginal.CELL));
            } else {
                draw();
            }

        });
        GlobalListenerManger.getInstance().onSelectListener(e -> {
                current.clear();
                setTempSelect(e);
                cost = e.getCost();
                System.out.println("Cost : " + cost);
                System.out.println("Budget" + current.size());
                if (cost <= current.size()) {
                    fireEmpty();
                } else {
                    fireDisable();
                    fireSacrificeable();
                }
        });

        GlobalListenerManger.getInstance().onOnplace(((index, isPlayer , card) -> {
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
    public void initHand() {

        Startdraw(getDeck(), START_NUM);
        add(new InstanceCard(CardOriginal.CELL));
    }

    @Override
    public void reInit() {
        getHand().clear();
        getDeck().clear();
        setDeck(new ArrayList<>(getDeckTemplate()));
        initHand();
    }

    public void fireEmpty() {
        GlobalListenerManger.getInstance().fireIsSlotEmpty(board.getIndexAvailableSlotList(true));
    }
    public void fireDisable() {
        GlobalListenerManger.getInstance().fireDisableListener();
    }
    public void fireSacrificeable() {
        GlobalListenerManger.getInstance().fireOnSacrificeable(board.getIndexUnavailableSlotList(true));
    }
    @Override
    void playTurn() {
        System.out.println("Choose What u gonna do");
        System.out.println("[1] - Draw");
        System.out.println("[2] - Place");
        Decidetion(scanner.nextInt());
    }

    public void SacPlace() {
        GlobalListenerManger.getInstance().firesacrificeplaceable(current);
    }

    public void checkCanplace() {
        if (cost <= current.size()) {
            fireEmpty();
        } else {
            fireSacrificeable();
        }
    }

    @Override
    public void add(InstanceCard card) {
        super.add(card);
        GlobalListenerManger.getInstance().fireAddCard(card);
    }
    public void fireAdd(InstanceCard card) {
        GlobalListenerManger.getInstance().fireAddCard(card);
    }

    @Override
    public void place(InstanceCard card, int index, boolean isPlayerSide) {
        super.place(card, index, isPlayerSide);
        for (Slot slot : board.getPlrSlot()) {
            if (current.contains(slot.getCard()) && board.getPlrSlot().indexOf(slot) != index) {
                int SacIndex = board.getPlrSlot().indexOf(slot);
                System.out.println("contain : " + slot.getCard().getName() + "in sacrifice || Index : " + board.getPlrSlot().indexOf(slot));
                board.setEmpty(SacIndex, true);
            }
        }
        GlobalListenerManger.getInstance().fireOnRemovefromhand(card);
    }
    public void Destroy() {

    }

    public void Remove() {

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


        show();
    }

    @Override
    public void Startdraw(List<InstanceCard> deck, int n) {
        System.err.println("DECk SIZE : " + getDeck().size());
        for (InstanceCard card : getDeck()) {
            System.out.println(card.getName());
        }

        super.Startdraw(deck, n);
        for (InstanceCard card : getHand()) {
            GlobalListenerManger.getInstance().fireAddCard(card);
        }
    }


}
