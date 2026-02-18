public class CardListenerManager {
    private AddCardListener addCardListener;
    private DrawListener drawListener;
    private SelectListener selectListener;

    public void onAddListener(AddCardListener listener) {
        this.addCardListener = listener;
    }

    public void onDrawListener(DrawListener drawListener) {
        this.drawListener = drawListener;
    }

    public void onSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public void FireDrawCard() {
        if (drawListener != null) {
            drawListener.onDraw();
        }
    }

    public void FireSelectListener(InstanceCard card) {
        if (selectListener != null) {
            selectListener.onSelect(card);
        }
    }


    public void FireAddCard(InstanceCard card) {
        if (addCardListener != null) {
            System.out.println("Fire OnAdd");
            addCardListener.onAdd(card);
        }
    };
}
