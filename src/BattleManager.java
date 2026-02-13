public class BattleManager {
    Board board;
    Player player;
    Player CPU;

    public BattleManager(Board board , Player player) {
        this.board = board;
        this.player = player;
        this.CPU = new PlayerCPU(Deck.STARTER_DECK);
        updateBoard();
    }
    public void show() {
        board.showBoard();
    }
    public void place(Card card, int index ,boolean isPlayer) {
        board.placeCard(new InstanceCard(card) , index , isPlayer);
        updateBoard();
    }

    public void Init() {
        while (true) {
            player.playTurn();
            updateBoard();
            execute(true);
            CPU.playTurn();
            updateBoard();
            execute(false);
            show();
        }
    }

    public void updateBoard() {
        CPU.setBoard(this.board);
        player.setBoard(this.board);
    }
    private void execute(boolean isPlayer) {
        board.play(isPlayer);
    }
}
