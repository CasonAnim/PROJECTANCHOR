import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.showBoard();

        Player player = new User(Deck.STARTER_DECK);

       BattleManager battleManager = new BattleManager(board , player , new AnchorInstance(AnchorOriginal.anchor_1));


//       battleManager.place(CardOriginal.GUARD, 0 ,true);
//       battleManager.place(CardOriginal.GUARD, 2 ,true);
//       battleManager.place(CardOriginal.JUGGERNAUT, 0 ,false);
//       battleManager.place(CardOriginal.JUGGERNAUT, 1 ,false);
//       battleManager.place(CardOriginal.JUGGERNAUT, 2 ,false);
//       battleManager.place(CardOriginal.JUGGERNAUT, 3 ,false);
       battleManager.show();
       battleManager.Init();





    }

}
