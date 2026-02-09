import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Player {
    Random rng = new Random();

    public static final int START_NUM = 5;
    private List<InstanceCard> hand;
    private List<InstanceCard> deck;


    public Player(Map<Card, Integer> deck) {
        this.hand = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.deck = Deck.InitDeck(deck);
        draw(this.deck, START_NUM);
        for (InstanceCard a : hand) {
            System.out.println("HAND : "+a.getName());
        }

        for (InstanceCard a : this.deck) {
            System.out.println("LEFTOVER : "+ a.getName());
        }
    }

    public void draw(List<InstanceCard> deck , int n) {
        int tempindex;
        for (int i = 0; i < n; i++) {
            tempindex = rng.nextInt(0, deck.size());
            hand.add(deck.get(tempindex));
            deck.remove(tempindex);
        }
    }
}
