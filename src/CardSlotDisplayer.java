import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardSlotDisplayer extends JPanel {
    private SlotDisplay plrSlot = new SlotDisplay();
    private SlotDisplay enemySlot = new SlotDisplay();
    CardSlotDisplayer() {
        this.setBackground(new Color(69, 57, 97));
        this.setLayout(null);
        this.add(plrSlot);
        this.add(enemySlot);

        GlobalListenerManger.getInstance().onOnPlacePaint((card, index, isPlayer) -> {
            if (isPlayer) {
                plrSlot.setCardDisplay(card, index);
            } else {
                enemySlot.setCardDisplay(card, index);
            }
        });

        GlobalListenerManger.getInstance().onIsSlotEmptyRequest(e -> {
            for (int i : e) {
                plrSlot.Available(i);
            }
        });


        GlobalListenerManger.getInstance().onDeSlectListener(() -> {
            for (FramewCrad i : plrSlot.getList().getList()) {
                if (i.isEmpty) {
                    i.clearplaceable();
                } else if(!i.isEmpty) {
                    i.placed();
                }
            }
        });

    }
    public void Init() {
        UIHelper.apply(this,1,0,0,0,1,0,0,0);
        UIHelper.apply(plrSlot,0.6,0,0.5,0,0.35,0,0.9,0,0.5,1);
        UIHelper.apply(enemySlot,0.6,0,0.5,0,0.35,0,0.02,0,0.5,0);
        enemySlot.InitEnemy();
        plrSlot.Init();
        plrSlot.setEmpty();
        enemySlot.setEmpty();
    }



//    public void updateDisplay() {
//        for (int i = 0; i < Board.SLOT_SIZE; i++) {
//            UIHelper.apply(list.get(i), 0.2,0,space * i , 0,1,0,0,0);
//        }
//    }
//    public void setSlot1(InstanceCard card) {
//
//    }




}
