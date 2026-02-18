public class BattleManager {
    Board board;
    Player player;
    Player CPU;

    AnchorInstance anchor;
    static final int MAXENEMYHP = 10;
    static int ENEMYHP = MAXENEMYHP;

    public BattleManager(Board board , Player player ,AnchorInstance anchor) {
        this.board = board;
        this.player = player;
        this.CPU = new PlayerCPU(Deck.ENEMY_DECK);
        this.anchor = anchor;
        updateBoard();

//        GlobalListenerManger.getInstance().onOnplace(((index, isPlayer) -> {
//            if (player.getTempSelect() != null) {
//                place(player.getTempSelect(), index, isPlayer);
//                player.clearSelect();
//                show();
//                player.showHand();
//            } else {
//                System.out.println("Select something");
//            }
//        }));
    }



    public void show() {
        board.showBoard();
        System.out.println("Anchor HP : " + anchor.selfHP);
        System.out.println("Enemy HP : " + ENEMYHP);
    }
    public void place(Card card, int index ,boolean isPlayer) {
        System.out.println("Temp : " + player.getTempSelect().getName());
        board.placeCard(new InstanceCard(card) , index , isPlayer);
        updateBoard();
        System.out.println("Temp : " + player.getTempSelect().getName());
    }
    public void place(InstanceCard card, int index ,boolean isPlayer) {
        System.out.println("Temp : " + player.getTempSelect().getName());
        board.placeCard(card , index , isPlayer);
        updateBoard();
        System.out.println("Temp : " + player.getTempSelect().getName());
    }
    public void execute() {
        execute(true);
    }
    public void Init() {
        while (true) {
            player.playTurn();

            updateBoard();
            execute(true);
            anchor.ability(board);
            updateStatusPlayer(true);
//            show();
//            CPU.playTurn();
            updateBoard();
            execute(false);
            anchor.ability(board);
            updateStatusPlayer(false);
//            show();
            if (checksomeonedie()) {
                if (playerDead()) {
                    System.out.println("CPU win");
                } else {
                    System.out.println("Player win");
                }
                break;
            }
        }
    }

    public void updateBoard() {
        CPU.setBoard(this.board);
        player.setBoard(this.board);
    }
    private void execute(boolean isPlayer) {
        board.play(isPlayer);
    }
    private boolean playerDead() {
        return anchor.selfHP == 0;
    }
    private boolean checksomeonedie() {
        return anchor.selfHP == 0 || ENEMYHP == 0;
    }
    private void updateStatusPlayer(boolean isPlayerside) {
        System.out.println("Direct : " + board.getDirectDMG());
        System.out.println("ANCHOR + Direct" + (anchor.selfHP+ board.getDirectDMG()));

        if (isPlayerside) {
            if (anchor.selfHP+board.getDirectDMG()>anchor.getMAXHP()){
                anchor.selfHP = anchor.getMAXHP();
            } else {
                anchor.selfHP+=board.getDirectDMG();
            }
            ENEMYHP -= board.getDirectDMG();

//            anchor.selfHP+=board.getDirectDMG();

        } else {
            if (ENEMYHP+board.getDirectDMG()>10){
                ENEMYHP =10;
            } else {
                ENEMYHP+=board.getDirectDMG();
            }
            anchor.takeDMG(board.getDirectDMG());
        }
        board.clearDMG();
    }
}
