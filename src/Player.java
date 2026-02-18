import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Player {
    private InstanceCard tempSelect;
    Random rng = new Random();
    Board board;
    public static final int START_NUM = 5;
    private List<InstanceCard> hand;
    private List<InstanceCard> deck;


    public Player(Map<Card, Integer> deck) {
        this.hand = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.deck = Deck.InitDeck(deck);
//        show();

    }

    public void Startdraw(List<InstanceCard> deck , int n) {
        int tempindex;
        for (int i = 0; i < n; i++) {
            tempindex = rng.nextInt(0, deck.size());
            hand.add(deck.get(tempindex));
            deck.remove(tempindex);
        }
    }
    public void draw() {
        int temp = rng.nextInt(0,deck.size());
        hand.add(deck.get(temp));

        deck.remove(temp);
    }
    public InstanceCard getTempSelect() {
        return tempSelect;
    }

    public void clearSelect() {
        tempSelect = null;
        GlobalListenerManger.getInstance().fireDeSelect();
    };
    public void setTempSelect(InstanceCard card) {
        this.tempSelect = card;
        System.out.println("Current I Pick " + tempSelect.getName());
    }
    public void show() {
        for (InstanceCard a : this.hand) {
            System.out.println("HAND : "+a.getName());
        }

        for (InstanceCard a : this.deck) {
            System.out.println("LEFTOVER : "+ a.getName());
        }
    }
    abstract void playTurn();

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<InstanceCard> getHand() {
        return hand;
    }

    public void setHand(List<InstanceCard> hand) {
        this.hand = hand;
    }

    public List<InstanceCard> getDeck() {
        return deck;
    }

    public void setDeck(List<InstanceCard> deck) {
        this.deck = deck;
    }

    public Board getBoard() {
        return board;
    }

    public void showHand () {
        for (InstanceCard card : hand) {
            System.out.println("[" + hand.indexOf(card) + "] - " + card.getName());
        }
    }

    public void place(InstanceCard card , int index, boolean isPlayerSide) {
        GlobalListenerManger.getInstance().fireonPlacePaint(card,index ,isPlayerSide);
        board.placeCard(card, index , isPlayerSide);
        hand.remove(card);
    }
    public void add(InstanceCard card) {
        hand.add(card);
    }
    public void Handremove(InstanceCard card) {
        hand.remove(card);
    }
    public void Deckremove(InstanceCard card) {
        deck.remove(card);
    }
}
