public class BattleManager {
    Board board;
    Player player;
    Player CPU;

    AnchorInstance anchor;
    boolean isGameEnd;
    static final int MAXENEMYHP = 10;
    static int ENEMYHP;

    public BattleManager(Board board , Player player ,AnchorInstance anchor) {
        this.board = board;
        this.player = player;
        this.CPU = new PlayerCPU(Deck.ENEMY_DECK);
        this.anchor = anchor;

        this.anchor.setSelfHP(anchor.getMAXHP());
        ENEMYHP =MAXENEMYHP;

        System.out.println("ENEMY HP : " + ENEMYHP);
        System.out.println("PLAYER HP : " + anchor.selfHP);
        updateBoard();
        player.showHand();

        GlobalListenerManger.getInstance().onEndturn(() -> {
            Init2();
        });



//        System.identityHashCode(this)



        GlobalListenerManger.getInstance().fireAfterBattleListener(anchor.selfHP, true);
        GlobalListenerManger.getInstance().fireAfterBattleListener(ENEMYHP, false);


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

    public void Init2() {
        board.play(true);
        board.updateDeadstate();

//        updateBoard();
        anchor.ability(board);
        board.updateDeadstate();
        updateStatusPlayer(true);
        checkgameResult();

//        updateBoard();
        if (!isGameEnd) {
            CPU.playTurn();
            board.updateDeadstate();
//        updateBoard();
            execute(false);
            board.updateDeadstate();
//        anchor.ability(board);
//        board.updateDeadstate();
            updateStatusPlayer(false);
            checkgameResult();
        }
//        else {
//            System.out.println("Game has been Finised");
//        }

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

    private void checkgameResult() {
        if (checksomeonedie()) {
            isGameEnd = true;
            if (playerDead()) {
                System.out.println("CPU win");
                GlobalListenerManger.getInstance().fireGameResult(false);
                GlobalListenerManger.getInstance().FireRemoteEvent(2, false);
            } else {
                System.out.println("Player win");
                GlobalListenerManger.getInstance().fireGameResult(true);
                GlobalListenerManger.getInstance().FireRemoteEvent(2, true);
            }
        }
    }

    private boolean checksomeonedie() {
        return anchor.selfHP == 0 || ENEMYHP <= 0;
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
        sendAncHPEnimHP();


        board.clearDMG();
    }
    public void sendAncHPEnimHP(){
        GlobalListenerManger.getInstance().fireAfterBattleListener(anchor.selfHP, true);
        GlobalListenerManger.getInstance().fireAfterBattleListener(ENEMYHP, false);
    }
}


