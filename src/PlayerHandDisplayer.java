import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayerHandDisplayer extends JPanel {


    private int index =0;
    List<InstanceCard> card = new ArrayList<>();
    List<FramewCrad> framewCrads = new ArrayList<>();
    PlayerHandDisplayer() {
        this.setLayout(null);

        GlobalListenerManger.getInstance().OnonRemove(e -> {

            System.out.println("Array : " + card.size());
            System.out.println("request Index : " + e);
            FramewCrad a = framewCrads.get(e);
            InstanceCard b = card.get(e);
            card.remove(b);
            framewCrads.remove(a);
            this.remove(a);
            updateHand();
        });

        GlobalListenerManger.getInstance().onCardPaint((e,v) -> {
            e.setNametag(v.getName());
            e.setDMG(String.valueOf(v.getDMG()));
            e.setHpText(String.valueOf(v.getHP()));
        });
        this.setBackground(new Color(69, 57, 97));
        GlobalListenerManger.getInstance().onAddListener(e -> {
            card.add(e);
            FramewCrad Frame = new FramewCrad();

            framewCrads.add(Frame);
            Frame.setName(String.valueOf(card.indexOf(e)));
            System.out.println("Construct Card no. " + Frame.name);
            framewCrads.get(card.indexOf(e)).BindCard(e);
            this.add(Frame);
            UIHelper.apply(Frame, 0.25,0,card.indexOf(e)*0.1,0,1,0,0,0);
            Frame.UpdateDisplay();
            Frame.yued();
            GlobalListenerManger.getInstance().fireCardpaint(Frame, e);
            Frame.removeBoarder();
            Frame.buttonselect.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Container frame = e.getComponent().getParent();
//                    System.out.println("Point at : " + frame.toString());
//                    System.out.println("Parent by : " + frame.getParent().toString());
                    frame.getParent().setComponentZOrder(frame, 0);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Container frame = e.getComponent().getParent();
                    for (int i = 0; i < framewCrads.size(); i++) {
                        frame.getParent().setComponentZOrder(framewCrads.get(i), i);
                    }
                }
            });
            Frame.setSelectAvailable();
        });
    }
    public void updateHand() {
        for (InstanceCard c : card) {
            UIHelper.apply(framewCrads.get(card.indexOf(c)), 0.25,0,card.indexOf(c)*0.1,0,1,0,0,0);
        }
    }

    public void Init() {
        UIHelper.apply(this, 0.7, 0 ,0.05,0,0.7,0,0.1,0);
    }
}
