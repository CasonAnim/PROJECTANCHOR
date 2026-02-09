import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        InstanceCard a1 = new InstanceCard(CardOriginal.TEST_NO1);
        InstanceCard a2 = new InstanceCard(CardOriginal.TEST_NO2);
        InstanceCard a3 = new InstanceCard(CardOriginal.TEST_NO3);
        InstanceCard a4 = new InstanceCard(CardOriginal.TEST_NO4);
        InstanceCard a5 = new InstanceCard(CardOriginal.TEST_NO1);
        InstanceCard a6 = new InstanceCard(CardOriginal.TEST_NO2);
        InstanceCard a7 = new InstanceCard(CardOriginal.TEST_NO3);
        InstanceCard a8 = new InstanceCard(CardOriginal.TEST_NO4);
        Slot slot_a1 = new Slot(a1);
        Board board = new Board();
        board.placeCard(a1, 0,true);
        board.placeCard(a2, 1,true);
        board.placeCard(a3, 2,true);
        board.placeCard(a4, 3,true);

        board.placeCard(a5, 0,false);
        board.placeCard(a6, 1,false);
        board.placeCard(a7, 2,false);
        board.placeCard(a8, 3,false);
        List<InstanceCard> testdeck = Deck.InitDeck(Deck.STARTER_DECK);
    }

}
