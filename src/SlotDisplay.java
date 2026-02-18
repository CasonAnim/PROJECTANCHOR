import javax.swing.*;
import java.awt.*;

public class SlotDisplay extends JPanel {
    Collector<FramewCrad> list = new Collector<>();

    SlotDisplay() {
//        this.setBackground(Color.WHITE);
        this.setOpaque(false);
        this.setLayout(null);

    }
    public void Init() {
        for (int i = 0; i < Board.SLOT_SIZE; i++) {
            FramewCrad a = new FramewCrad();
            System.out.println("Made :" +i);
            String name = String.valueOf(i);
            System.out.println("Name ID :" +name);

            list.push(a);
            this.add(list.getData(i));
            UIHelper.apply(list.getData(i),0.2,0,0.25*i , 0,1,0,0,0);
            list.getData(i).UpdateDisplay();
            list.getData(i).yued();
            list.getData(i).setName(name);
            System.out.println(list.getData(i).getName());


        }
    }

    public Collector<FramewCrad> getList() {
        return list;
    }

    public void InitEnemy() {
        for (int i = 0; i < Board.SLOT_SIZE; i++) {
            FramewCrad a = new FramewCrad();
            System.out.println("Made :" +i);
            String name = String.valueOf(i);
            System.out.println("Name ID :" +name);

            list.push(a);
            this.add(list.getData(i));
            UIHelper.apply(list.getData(i),0.2,0,0.25*i , 0,1,0,0,0);
            list.getData(i).UpdateDisplay();
            list.getData(i).yued();
            list.getData(i).Enim();
            list.getData(i).setName(name);
            System.out.println(list.getData(i).getName());
//            list.getData(i).setEmpty();

        }
    }

    public void isContain() {

    }


    public void setEmpty() {
        for (FramewCrad n : list.getList()) {
            n.setEmpty();
        }
    }

    public void Available(int index) {
        list.getData(index).setAvailable();
    }
    public void setCardDisplay(InstanceCard card ,int index) {
        FramewCrad a = list.getData(index);
        a.setNametag(card.getName());
        a.setDMG(String.valueOf(card.getDMG()));
        a.setHpText(String.valueOf(card.getHP()));
        a.setVisible();
        a.removeBoarder();
        a.setEmptyStatus(false);
    }
}
