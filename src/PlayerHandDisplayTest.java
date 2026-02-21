public class PlayerHandDisplayTest {
    public static void main(String[] args) {
        CardListenerManager manager = new CardListenerManager();
        ITHINKTHISISMYFINALMYFROM ithinkthisismyfinalmyfrom = new ITHINKTHISISMYFINALMYFROM();
        Board board = new Board();
        Player user = new User(Deck.STARTER_DECK);
        BattleManager battleManager = new BattleManager(board, user,  new AnchorInstance(AnchorOriginal.anchor_1));

    }
}
