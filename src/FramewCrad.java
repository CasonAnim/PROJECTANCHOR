import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FramewCrad extends JPanel {
    Font font30 = new Font("Arial", Font.BOLD, 30);
    Font font15 = new Font("Arial", Font.BOLD, 15);
    String name;
    boolean isEmpty;
    private boolean isSac;
    private boolean isSacSwitch;
    JLabel nametag = new JLabel("PLACEHOLDER");
    JLabel charpic = new JLabel();
    JLabel dmgText = new JLabel("0");
    JLabel dmgIcon = new JLabel();
    JLabel hpText = new JLabel("0");
    JLabel hpIcon = new JLabel();
    JButton buttonselect = new JButton();
    private JButton sacrifice = new JButton();
    private JButton placable = new JButton();
    private JLabel frame = new JLabel();
    private InstanceCard ID = null;
    private ImageIcon cardframe = new ImageIcon("asset/texture/cardframe.png");
    private ImageIcon aticon = new ImageIcon("asset/texture/aticon.png");
    private ImageIcon hpicon = new ImageIcon("asset/texture/hpicon.png");

    ArrayList<JLabel> c = new ArrayList<>();
    ArrayList<JButton> butty = new ArrayList<>();
    FramewCrad() {

        butty.add(placable);
        butty.add(buttonselect);
        butty.add(sacrifice);
        c.add(nametag);
        c.add(charpic);
        c.add(hpIcon);
        c.add(hpText);
        c.add(dmgIcon);
        c.add(dmgText);
        c.add(frame);
        frame.setOpaque(false);
        frame.setIcon(cardframe);
        hpIcon.setIcon(hpicon);
        dmgIcon.setIcon(aticon);
        for (JButton a : butty) {
            this.add(a);
            a.setEnabled(false);
            a.setContentAreaFilled(false);
            a.setFocusPainted(false);
            a.setOpaque(false);
        }
        for (JLabel label : c) {
            this.add(label);
//            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            label.setHorizontalAlignment(JLabel.CENTER);
        }
        hpText.setFont(font30);
        dmgText.setFont(font30);

        this.setOpaque(false);
//        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.setBackground(Color.BLACK);

        this.setBorder(BorderFactory.createLineBorder(Color.WHITE , 2));

        buttonselect.addActionListener(e -> {
            System.out.println("Pressed! , IM A " + ID.getName());
            GlobalListenerManger.getInstance().fireSelectListener(ID);

        });
        this.setLayout(null);
        placable.addActionListener(e -> {
            System.out.println("place!");
            isSac = false;
            GlobalListenerManger.getInstance().fireOnplace(Integer.parseInt(name),true, ID);

        });

        this.setBackground(Color.WHITE);
//        this.nametag.setVisible(true)
        sacrifice.addActionListener(e -> {
            if (isSacSwitch) {
                isSac = true;
            }
            System.out.println(ID.getName() + "gonna get sacrifice");
            fireValue();
            sacrifice.setEnabled(false);
            setBorder(BorderFactory.createLineBorder(Color.WHITE , 2));
        });

    }

    public void setID(InstanceCard ID) {
        this.ID = ID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    public void setSacrificeable() {
        for (JButton button : butty) {
            button.setVisible(false);
        }
        sacrifice.setVisible(true);
        sacrifice.setEnabled(true);
        this.setBorder(BorderFactory.createLineBorder(Color.RED , 5));
        isSacSwitch = true;
    }

    public void setPlacable() {
        for (JButton comp : butty) {
            comp.setVisible(false);
        }
        placable.setEnabled(true);
        placable.setVisible(true);

    }
    public void setEmpty() {
        for (Component comp : c) {
            comp.setVisible(false);
        }
//        buttonselect.setContentAreaFilled(false);
//        buttonselect.setFocusPainted(false);
//        buttonselect.setEnabled(false);
        isEmpty = true;
        setBorder(BorderFactory.createLineBorder(Color.WHITE , 2));
    }
    public void resetSac() {
        isSac = false;
    }

//    public void

    public void setAvailable() {
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE , 5));
        buttonselect.setVisible(false);
        sacrifice.setVisible(false);
        placable.setEnabled(true);
        placable.setVisible(true);
    }
    public void setSelectAvailable() {
        placable.setVisible(false);
        buttonselect.setEnabled(true);
    }

    public void Enim() {
        this.buttonselect.setEnabled(false);
    }

    public void setEmptyStatus(boolean empty) {
        isEmpty = empty;
    }

    public void setVisible() {
        for (Component comp : c) {
            comp.setVisible(true);
        }
    }
    public void setNametag(String nametag) {
        this.nametag.setText(nametag);
    }
    public void setHpText(String nametag) {
        this.hpText.setText(nametag);
    }public void setDMG(String nametag) {
        this.dmgText.setText(nametag);
    }

    public void BindCard(InstanceCard card) {
        ID = card;
    }

    public void UpdateDisplay() {
        UIHelper.apply(nametag, 1 , 0, 0 ,0 ,0.1, 0 ,0 ,0);
        UIHelper.apply(charpic, 0.65 , 0, 0.5 ,0 ,0.38, 0 ,0.17 ,0,0.5,0);
        UIHelper.apply(dmgIcon, 0.25 , 0, 0 ,0 ,0.15, 0 ,0.6 ,0);
        UIHelper.apply(dmgText, 0.25 , 0, 0 ,0 ,0.3, 0 ,0.65 ,0);
        UIHelper.apply(hpIcon, 0.25 , 0, 1 ,0 ,0.15, 0 ,0.6 ,0,1,0);
        UIHelper.apply(hpText, 0.25 , 0, 1 ,0 ,0.3, 0 ,0.65 ,0,1,0);
        UIHelper.apply(buttonselect, 1 , 0, 0 ,0 ,1, 0 ,0,0);
        UIHelper.apply(sacrifice, 1 , 0, 0 ,0 ,1, 0 ,0,0);
        UIHelper.apply(placable, 1 , 0, 0 ,0 ,1, 0 ,0,0);
        UIHelper.apply(frame, 1,0,0,0,1,0,0,0);
    }

    public void yued() {
        int w = this.getWidth();
        int h = this.getHeight();
        Image img = cardframe.getImage().getScaledInstance(w, h ,Image.SCALE_SMOOTH);
        Image img2 = aticon.getImage().getScaledInstance(hpIcon.getWidth(), hpIcon.getHeight() ,Image.SCALE_SMOOTH);
        Image img3 = hpicon.getImage().getScaledInstance(dmgIcon.getWidth(), dmgIcon.getHeight() ,Image.SCALE_SMOOTH);
        this.frame.setIcon(new ImageIcon(img));
        this.dmgIcon.setIcon(new ImageIcon(img2));
        this.hpIcon.setIcon(new ImageIcon(img3));
    }

    public void removeBoarder() {
        this.setBorder(null);
    }

    public void clearplaceable() {
        removeBoarder();
        for (JButton b : butty) {
            b.setVisible(false);
        }
        setBorder(BorderFactory.createLineBorder(Color.WHITE , 2));
    }

    public boolean isSac() {
        return isSac;
    }

    public void placed() {
        for (JButton b : butty) {
            b.setVisible(false);
        }
    }

    public void fireValue() {
        GlobalListenerManger.getInstance().fireSacrificeSelectListener(ID);
    }
}
