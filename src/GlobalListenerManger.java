import java.util.ArrayList;
import java.util.List;

public class GlobalListenerManger {
    private static GlobalListenerManger instance;
    private List<SelectListener> selectListener = new ArrayList<>();
    private AddCardListener addCardListener;
    private DrawListener drawListener;
    private CardListener cardListener;
    private DeSlectListener deSlectListener;
    private isSlotEmptyRequest isSlotEmptyRequest;
    private onSmthTst onSmthTst;
    private List<onRemovefromhand> onRemovefromhand = new ArrayList<>();
    private OnPlace onPlace;
    private List<onPlacePaint> onPlacePaint = new ArrayList<>();
    private onEndTurn onendTurn;
    private DeathListener ondeath;
    private TakeDMGListener takeDMGListener;
    private SacrificeableListener sacrificeableListener;
    private SacrificeSelectListener sacrificeSelectListener;
    private onSacrificeplaceable onSacrificeplaceable;
    private DisableListener disableListener;
    private EndturnListener endturnListener;
    private AfterBattleListener afterBattleListener;
    private GameResultListener gameResultListener;
    private List<UIListener> uiListener = new ArrayList<>();
    private List<RemoteSave> remoteSaves = new ArrayList<>();


    private GlobalListenerManger() {}

    public static GlobalListenerManger getInstance() {
        if (instance == null) {
            instance = new GlobalListenerManger();
        }
        return instance;
    }

    public void onSacrificeable(SacrificeableListener sacrificeableListener) {
        this.sacrificeableListener = sacrificeableListener;
    }

    public void fireOnSacrificeable(List<Integer> list) {
        if (sacrificeableListener != null) {
            sacrificeableListener.onSac(list);
        }
    }

    public void onsacrificeplaceable(onSacrificeplaceable onSacrificeplaceable) {
        this.onSacrificeplaceable = onSacrificeplaceable;
    }



    public void firesacrificeplaceable(List<InstanceCard> list) {
        if (onSacrificeplaceable != null) {
            onSacrificeplaceable.onplace(list);
        }
    }


    public void onUiListener(UIListener uiListener) {
        this.uiListener.add(uiListener);
    }

    public void fireUiListener(int index) {
        for (UIListener uiListener1 : uiListener) {
            System.err.println("Hello");
            uiListener1.onMove(index);
        }
    }


    public void OnRemoteEvent(RemoteSave remoteSave) {
        this.remoteSaves.add(remoteSave);
    }

    public void FireRemoteEvent(int Channel, Object data) {
        for (RemoteSave i : remoteSaves) {
            i.onRemote(Channel, data);
        }
    }

    public void onGameResult (GameResultListener gameResultListener) {
        this.gameResultListener = gameResultListener;
    }

    public void fireGameResult (boolean isPlayerWin) {
        if (gameResultListener != null) {
            gameResultListener.onResult(isPlayerWin);
        }
    }


    public void onAfterBattleListener(AfterBattleListener afterBattleListener) {
        this.afterBattleListener = afterBattleListener;
    }
    public void fireAfterBattleListener(int HP, boolean isPlayer) {
        if (afterBattleListener != null) {
            afterBattleListener.onAfterBattle(HP, isPlayer);
        }
    }

    public void onBotEndTurn(EndturnListener endturnListener) {
        this.endturnListener = endturnListener;
    }

    public void fireBotEndTurn() {
        if (endturnListener != null) {
            endturnListener.onEnd();
        }
    }


    public void onDisableListener(DisableListener disableListener) {
        this.disableListener = disableListener;
    }
    public void fireDisableListener() {
        if (disableListener != null) {
            disableListener.Stop();
        }
    }

    public void onSacrificeSelectListener(SacrificeSelectListener sacrificeSelectListener) {
        this.sacrificeSelectListener = sacrificeSelectListener;
    }
    public void fireSacrificeSelectListener(InstanceCard card) {
        if (sacrificeSelectListener != null) {
            sacrificeSelectListener.saggy(card);
        }
    }

    public void onDeath(DeathListener ondeath) {
        this.ondeath = ondeath;
    }

    public void fireDeath(int index ,boolean isPlayerSide) {
        if (ondeath != null) {
            ondeath.ded(index, isPlayerSide);
        }
    }
    public void ontakeDMG(TakeDMGListener takeDMGListener) {
        this.takeDMGListener = takeDMGListener;
    }

    public void firetakeDMG(int index,InstanceCard card, boolean isPlayerSide) {
        if (takeDMGListener != null) {
            takeDMGListener.dmg(index ,card , isPlayerSide);
        }
    }


    public void onSelectListener(SelectListener selectListener) {
        this.selectListener.add(selectListener);
    }

    public void fireSelectListener(InstanceCard card) {
        for (SelectListener listener : selectListener) {
            listener.onSelect(card);
        }
    }

    public void onOnplace(OnPlace onPlace) {
        this.onPlace = onPlace;
    }

    public void fireonPlacePaint(InstanceCard card, int index,boolean isPlayer) {
        for (onPlacePaint event : onPlacePaint) {
            event.OnPlacePaint(card,index ,isPlayer);
        }
    }

    public void onOnPlacePaint(onPlacePaint onPlacePaint) {
        this.onPlacePaint.add(onPlacePaint);
    }

    public void fireOnplace(int index, boolean isPlayer, InstanceCard card) {
        if (onPlace != null) {
            onPlace.onplace(index,isPlayer, card);
        }
    }



    public void onDrawListener(DrawListener drawListener) {
        this.drawListener = drawListener;
    }

    public void fireDrawCard(boolean isDrawCell) {
        if (drawListener != null) {
            drawListener.onDraw(isDrawCell);
        }
    }


    public void onAddListener(AddCardListener listener) {
        this.addCardListener = listener;
    }

    public void fireAddCard(InstanceCard card) {
        if (addCardListener != null) {
            System.out.println("Fire OnAdd");
            addCardListener.onAdd(card);
        }
    }

    public void onCardPaint(CardListener listener) {
        this.cardListener = listener;
    }
    public void fireCardpaint(FramewCrad canvas, InstanceCard target) {
        System.out.println("เปลี่ยนจากปลายพู่กัน");
        if (cardListener != null) {
            cardListener.ondisplay(canvas, target);
        }
    }

    public void onDeSlectListener(DeSlectListener deSlectListener) {
        this.deSlectListener = deSlectListener;
    }

    public void fireDeSelect() {
        if (deSlectListener != null) {
            deSlectListener.onDeSelect();
        }
    }

    public void onIsSlotEmptyRequest(isSlotEmptyRequest isSlotEmptyRequest) {
        this.isSlotEmptyRequest = isSlotEmptyRequest;
    }

    public void fireIsSlotEmpty(List<Integer> list) {
        this.isSlotEmptyRequest.onIsEmpty(list);
    }

    public void onOnSmthTst(onSmthTst onSmthTst) {
        this.onSmthTst = onSmthTst;
    }

    public void fireOnsmthTest(String string) {
        if (onSmthTst != null) {
            onSmthTst.ontest(string);
        }
    }

    public void OnonRemovefromHand(onRemovefromhand onRemovefromhand) {
        this.onRemovefromhand.add(onRemovefromhand);
    }

    public void fireOnRemovefromhand(InstanceCard card) {
        for (onRemovefromhand event : onRemovefromhand) {
            event.onRemovem(card);
        }
    }

    public void onEndturn(onEndTurn onendTurn) {
        this.onendTurn = onendTurn;
    }
    public void  fireEndTurn() {
        if (onendTurn != null) {
            onendTurn.end();
        }
    }
}
