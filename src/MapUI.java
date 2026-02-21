import javax.swing.*;
import java.awt.*;

public class MapUI extends JPanel {
    private int currentStage = 4;
    private JPanel Screen = new JPanel();
    private JPanel islandDisplay = new JPanel();
    private JPanel icon = new JPanel();
    private ImageIcon islandicon = new ImageIcon("asset/texture/island.png");
    private ImageIcon marker = new ImageIcon("asset/texture/pointer.png");
    private ImageIcon bossicon = new ImageIcon("asset/texture/death.png");
    private JButton start = new JButton("Start Battle");
    private GridBagConstraints gbc = new GridBagConstraints();

    MapUI() {
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        setLayout(null);
        setBackground(new Color(84, 84, 84));
        start.setBackground(new Color(84, 84, 84));
        start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        start.setForeground(Color.BLACK);
        start.setFont(new Font("Arial", Font.BOLD, 45));
        Screen.setBackground(new Color(120, 93, 153));
        islandDisplay.setBackground(Color.WHITE);
        islandDisplay.setLayout(new GridBagLayout());
        icon.setLayout(new GridBagLayout());
        islandDisplay.setOpaque(false);
        icon.setOpaque(false);

        for (int i = 0; i < 5; i++) {
            Image tempImg;
            if (i!=4) {
                tempImg = islandicon.getImage();
            } else {
                tempImg = bossicon.getImage();

            }
            gbc.insets= new Insets(0,10,0,10);
            JLabel island = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    g.drawImage(tempImg, 0,0,getWidth(),getHeight(),this);
                }
            };
            islandDisplay.add(island, gbc);
            System.out.println(island.getSize());
        }
//        islandDisplay.setOpaque(false);

        add(start);
        add(Screen);
        add(islandDisplay);
        add(icon);
        setMarker(3);

        start.addActionListener(e -> {
            GlobalListenerManger.getInstance().fireUiListener(2);
        });

    }

    public void Init() {
        UIHelper.apply(Screen, 0.9 ,0 ,0.5,0 ,0.9,0,0.5,0,0.5,0.5);
        UIHelper.apply(islandDisplay, 0.7 ,0 ,0.5,0 ,0.2,0,0.5,0,0.5,0.5);
        UIHelper.apply(icon, 0.7 ,0 ,0.5,0 ,0.23,0,0.35,0,0.5,0.5);
        UIHelper.apply(start, 0.3 ,0 ,0.5,0 ,0.1,0,0.98,0,0.5,1);
//
        setComponentZOrder(islandDisplay ,0);
        setComponentZOrder(icon ,0);
    }

    public void setMarker(int StageIndex) {

        for (int i = 0; i < 5; i++) {
            if (i==StageIndex) {
                System.out.println("IM here");
                JLabel pointer = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.drawImage(marker.getImage(), 0,0,getWidth(),getHeight(),this);
                    }
                };
                icon.add(pointer, gbc);
            } else {
                JLabel p = new JLabel();

                icon.add(p, gbc);
            }
        }
    }
}
