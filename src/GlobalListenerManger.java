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
    private List<onRemove> onRemove = new ArrayList<>();
    private OnPlace onPlace;
    private List<onPlacePaint> onPlacePaint = new ArrayList<>();

    private GlobalListenerManger() {}

    public static GlobalListenerManger getInstance() {
        if (instance == null) {
            instance = new GlobalListenerManger();
        }
        return instance;
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

    public void fireOnplace(int index, boolean isPlayer) {
        if (onPlace != null) {
            onPlace.onplace(index,isPlayer);
        }
    }

    public void onDrawListener(DrawListener drawListener) {
        this.drawListener = drawListener;
    }

    public void fireDrawCard() {
        if (drawListener != null) {
            drawListener.onDraw();
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

    public void OnonRemove(onRemove onRemove) {
        this.onRemove.add(onRemove);
    }

    public void fireOnRemove(int index) {
        for (onRemove event : onRemove) {
            event.onRemovem(index);
        }
    }
}
