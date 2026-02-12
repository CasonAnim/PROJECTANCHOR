import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.showBoard();

        Player player = new User(Deck.STARTER_DECK);
        BattleManager battleManager = new BattleManager(board , player);
        battleManager.Init();

    }

}
