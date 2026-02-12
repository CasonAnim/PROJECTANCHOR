public class BattleManager {
    Board board;
    Player player;
    Player CPU;

    public BattleManager(Board board , Player player) {
        this.board = board;
        this.player = player;
        this.CPU = new PlayerCPU(Deck.STARTER_DECK);
    }

    public void Init() {
        player.playTurn();
    }
}
