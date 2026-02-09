import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Deck {
    public static final Map<Card, Integer> STARTER_DECK = Map.of(
            CardOriginal.GUARD, 5,
            CardOriginal.JUGGERNAUT, 2,
            CardOriginal.RIFLEMAN,4);

    public static List<InstanceCard> InitDeck(Map<Card, Integer> deck) {
        List<InstanceCard> deckInstance = new ArrayList<>();
        for (Map.Entry<Card, Integer> a : deck.entrySet()) {
            for (int i = 0; i < a.getValue(); i++) {
                deckInstance.add(new InstanceCard(a.getKey()));
                System.out.println("ADDED " + a.getKey().getName() + " to Deck [" + i +"]");
            }
        }
        return deckInstance;
    }
}
