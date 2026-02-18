public class PlayerHandDisplayTest {
    public static void main(String[] args) {
        CardListenerManager manager = new CardListenerManager();
        UIMainFrame mainFrame = new UIMainFrame();
        Board board = new Board();
        Player user = new User(Deck.STARTER_DECK);


        BattleManager battleManager = new BattleManager(board, user,  new AnchorInstance(AnchorOriginal.anchor_1));

    }
}
