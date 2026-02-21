import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameMenu extends JPanel {
    private JLabel title = new JLabel("PROJECT:ANCHOR");
    private JButton play = new JButton("Play");
    private JButton load = new JButton("Load Save");
    private Font font = new Font("Arial", Font.BOLD, 75);
    ArrayList<Component> comp = new ArrayList<>();
    GameMenu() {
        comp.add(title);
        comp.add(play);
        comp.add(load);
        setLayout(null);
        for (Component n : comp) {
            add(n);
            n.setFont(font);
            n.setForeground(Color.WHITE);
        }

        title.setHorizontalAlignment(JLabel.CENTER);
        transparentButton(load);
        transparentButton(play);

        play.addActionListener(e -> {
            System.out.println("Lets play !");
            GlobalListenerManger.getInstance().fireUiListener(0);
        });
        load.addActionListener(e -> {
            System.out.println("Looking For a Save!");
            GlobalListenerManger.getInstance().fireUiListener(1);
        });
        setBackground(Color.BLACK);

    }

    public void Init() {
        System.out.println("IM MENU");
        UIHelper.apply(title, 0.75,0,0.5,0,0.25,0,0.1,0,0.5,0.5);
        UIHelper.apply(play, 0.45,0,0.5,0,0.15,0,0.5,0,0.5,0.5);
        UIHelper.apply(load, 0.45,0,0.5,0,0.15,0,0.67,0,0.5,0.5);
        System.out.println(play.getWidth());
    }
    private void transparentButton(JButton target) {
        target.setContentAreaFilled(false);
        target.setFocusPainted(false);
        target.setBorder(BorderFactory.createLineBorder(Color.WHITE , 3));
        target.setOpaque(false);
    }
}
