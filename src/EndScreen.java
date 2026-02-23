import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EndScreen extends JPanel {
    private JLabel txt = new JLabel("PLACEHOLDER");
    private JButton Continue = new JButton("Continue");
    private Font font = new Font("Arial", Font.BOLD, 75);
    private JPanel panel = new JPanel();
    private List<Card> Pool = new ArrayList<>();
    private List<FramewCrad> LootboxDisplay = new ArrayList<>();
    private List<FramewCrad> Lootbox = new ArrayList<>();
    private Random rng = new Random();
    private Card select;
    EndScreen() {
        Pool.add(CardOriginal.HAMMERBOT);
        Pool.add(CardOriginal.DASHOCKER);
        Pool.add(CardOriginal.TEST_NO1);
        Pool.add(CardOriginal.TEST_NO2);
        Pool.add(CardOriginal.Glitch);
        Pool.add(CardOriginal.TEST_NO4);
        txt.setFont(font);
        txt.setOpaque(false);
        txt.setForeground(Color.WHITE);
        txt.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        txt.setHorizontalAlignment(JLabel.CENTER);
        setBackground(new Color(43,43,43));
        setLayout(null);
        add(txt);
        add(Continue);
        add(panel);
        panel.setLayout(null);
        panel.setOpaque(false);






    }
    public void Init() {
        UIHelper.apply(txt, 0.8,0,0.5,0,0.2,0,0.2,0,0.5,0.5);
        UIHelper.apply(Continue, 0.5,0,0.5,0,0.1,0,0.95,0,0.5,1);
        UIHelper.apply(panel, 0.5,0,0.5,0,0.45,0,0.8,0,0.5,1);
    }

    public void setTxt(String txt) {
        this.txt.setText(txt);
    }

    public void setwin() {
        Continue.setEnabled(false);
        panel.removeAll();

        Continue.addActionListener(e -> {
            GlobalListenerManger.getInstance().FireRemoteEvent(4, select);
            GlobalListenerManger.getInstance().FireRemoteEvent(3, 2);
        });
    }
    public void setLose() {
        panel.removeAll();
        Continue.addActionListener(e -> {
            GlobalListenerManger.getInstance().FireRemoteEvent(3, 1);
            GlobalListenerManger.getInstance().FireRemoteEvent(7, null);
        });
    }

    public void InitLoot() {
        for (int i = 0; i < 3; i++) {
            JButton button = new JButton();
            button.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            button.setContentAreaFilled(false);
            button.setOpaque(false);

            int index = rng.nextInt(Pool.size());
            Card b = Pool.get(index);
            JPanel panel1 = new JPanel();
            panel1.setBackground(Color.BLACK);
            FramewCrad framewCrad = new FramewCrad();
            LootboxDisplay.add(framewCrad);
            System.err.println("[LOOTBOX] - " + b.getName());
            panel.add(framewCrad);
            panel.add(button);
            UIHelper.apply(framewCrad, 0.3, 0,i*0.35,0,1,0,0,0);
            framewCrad.setVisible();
            framewCrad.UpdateDisplay();
            framewCrad.yued();
            framewCrad.setNametag(b.getName());
            framewCrad.setCharAva(b.getPic());
            framewCrad.setHpText(String.valueOf(b.getMaxHP()));
            framewCrad.setDMG(String.valueOf(b.getAT()));


            UIHelper.apply(button, 0.3, 0,i*0.35,0,1,0,0,0);
            panel.setComponentZOrder(button, 0);
            button.addActionListener(e -> {
                select = b;
                Continue.setEnabled(true);
                System.out.println(select.getName());
            });

        }
    }
}
