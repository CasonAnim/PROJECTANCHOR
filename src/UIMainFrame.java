import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIMainFrame extends JFrame {
    private JButton up = new JButton();
    private JButton down = new JButton();
    private JButton draw = new JButton("Draw");
    private JButton End = new JButton("ENDTURN");

    private PlayerHandDisplayer hand = new PlayerHandDisplayer();
    private CardSlotDisplayer board = new CardSlotDisplayer();
    UIMainFrame() {

        KeyStroke PressW = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        KeyStroke PressA = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);


        this.add(draw);
        this.add(End);
        up.setText("/\\");
        up.setHorizontalTextPosition(JLabel.CENTER);
        down.setText("\\/");
        down.setHorizontalTextPosition(JLabel.CENTER);
        down.setVisible(false);
        down.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                switchToHand();
            }
        });
        up.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                switchToBoard();
            }
        });
//        this.setSize(1280,720);
        this.setSize(640,360);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("PROJECT:ANCHOR");
        this.setVisible(true);
        this.setLayout(null);

        this.add(board);
        this.add(hand);
        this.add(up);
        this.add(down);

        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();

        board.setVisible(false);
        board.Init();
        UIHelper.apply(draw, 0.15, 0,0.8,0,0.2,0,0.2,0);
        UIHelper.apply(End, 0.15, 0,0.8,0,0.2,0,0.5,0);
        UIHelper.apply(up, 0.45, 0, 0.5, 0, 0.05,0,0.02,0,0.5,0);
        UIHelper.apply(down, 0.45, 0, 0.5, 0, 0.03,0,0.98,0,0.5,1);
        hand.Init();

        up.addActionListener(e -> {
            switchToBoard();
//            UIHelper.apply(down, 0.45, 0, 0.5, 0, 0.03,0,0.98,0,0.5,1);
        });
        down.addActionListener(e -> {
            switchToHand();
            down.getParent().setComponentZOrder(up,0);
        });
        draw.addActionListener(e -> {
            GlobalListenerManger.getInstance().fireDrawCard();
//            manager.FireDrawCard();
        });



        Action actionA = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToHand();
                System.out.println("Press S");
            }
        };
        Action actionW = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBoard();
            }
        };

        inputMap.put(PressA, "gay");
        actionMap.put("gay", actionA);
        inputMap.put(PressW, "actionW");
        actionMap.put("actionW", actionW);
    }
    public void switchToBoard(){
        board.setVisible(true);
        hand.setVisible(false);
        up.setVisible(false);
        down.setVisible(true);
        draw.setVisible(false);
        End.setVisible(false);
        up.getParent().setComponentZOrder(down,0);
    };

    public void switchToHand(){
        board.setVisible(false);
        up.setVisible(true);
        hand.setVisible(true);
        down.setVisible(false);
        draw.setVisible(true);
        End.setVisible(true);
        down.getParent().setComponentZOrder(up,0);
    };
}
