import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    public static final Map<Card, Integer> STARTER_DECK = Map.of(
            CardOriginal.GUARD, 5,
            CardOriginal.JUGGERNAUT, 2,
            CardOriginal.RIFLEMAN,4);
    public static final Map<Card, Integer> ADMIN_DECK = Map.of(
            CardOriginal.GOD, 5);

    public static final Map<Card, Integer> ENEMY_DECK = Map.of(
            CardOriginal.Glitch, 5 ,
            CardOriginal.TEST_NO2, 4,
            CardOriginal.CGUARD,5
//            CardOriginal.TEST_NO1,5
    );

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
