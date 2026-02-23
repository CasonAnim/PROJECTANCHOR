import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIMainFrame extends JPanel {

    private JButton up = new JButton();
    private JButton down = new JButton();
    private JButton draw = new JButton("DRAW");
    private JButton drawCell = new JButton("DRAWCELL");
    private JButton End = new JButton("ENDTURN");
    private EndScreen endScreen = new EndScreen();


    private PlayerHandDisplayer hand = new PlayerHandDisplayer();
    private CardSlotDisplayer board = new CardSlotDisplayer();
    UIMainFrame() {

        KeyStroke PressW = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        KeyStroke PressA = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);

        this.add(drawCell);
        this.add(draw);
        this.add(End);
        this.add(endScreen);
        endScreen.setVisible(false);


        UIHelper.apply(endScreen, 1, 0 ,0,0,1,0,0,0);
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

//        this.setSize(640,360);

        this.setVisible(true);
        this.setLayout(null);

        this.add(board);
        this.add(hand);
        this.add(up);
        this.add(down);

        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        board.setVisible(false);




        End.setEnabled(false);

        up.addActionListener(e -> {
            switchToBoard();
//            UIHelper.apply(down, 0.45, 0, 0.5, 0, 0.03,0,0.98,0,0.5,1);
        });
        down.addActionListener(e -> {
            switchToHand();
            down.getParent().setComponentZOrder(up,0);
        });
        draw.addActionListener(e -> {
            GlobalListenerManger.getInstance().fireDrawCard(false);
            afterDraw();
        });
        drawCell.addActionListener(e -> {
            GlobalListenerManger.getInstance().fireDrawCard(true);
            afterDraw();
        });

        ResetDraw();
//
        End.addActionListener(e -> {
            GlobalListenerManger.getInstance().fireEndTurn();
            End.setEnabled(false);
            System.out.println("End !");
        });
        GlobalListenerManger.getInstance().onBotEndTurn(() -> {
            draw.setEnabled(true);
            drawCell.setEnabled(true);
        });
        this.setBackground(new Color(99, 76, 147));
        GlobalListenerManger.getInstance().OnRemoteEvent(((Channel, data) -> {
            if (Channel==3) {
                if ((int) data==1) {
                    endScreen.setVisible(false);
                } else if ((int) data==2) {
                    Enable();
                }
            } else if (Channel==4) {
                endScreen.setVisible(false);
            } else if (Channel==7) {
                endScreen.setVisible(false);
                up.setVisible(true);
                down.setVisible(true);
                draw.setVisible(true);
                draw.setEnabled(true);
                drawCell.setVisible(true);
                drawCell.setEnabled(true);
                End.setVisible(true);
                hand.setVisible(true);
            }
        }));

        GlobalListenerManger.getInstance().onGameResult(e-> {
            endScreen.setVisible(true);
//            remove(up);
//            remove(down);
            up.setVisible(false);
            down.setVisible(false);
            draw.setVisible(false);
            drawCell.setVisible(false);
            End.setVisible(false);
            hand.setVisible(false);

            UIHelper.apply(endScreen,1,0,0,0,1,0,0,0);
            endScreen.Init();
            if (e) {
                endScreen.setTxt("YOU WIN");
                endScreen.setwin();
                endScreen.InitLoot();
            } else {
                endScreen.setTxt("YOU LOSE");
                endScreen.setLose();
            }





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
        drawCell.setVisible(false);
        End.setVisible(true);
        up.getParent().setComponentZOrder(down,0);
    };

    public void switchToHand(){
        board.setVisible(false);
        up.setVisible(true);
        hand.setVisible(true);
        down.setVisible(false);
        draw.setVisible(true);
        drawCell.setVisible(true);
        End.setVisible(true);
        down.getParent().setComponentZOrder(up,0);
    };

    public void afterDraw() {
        draw.setEnabled(false);
        drawCell.setEnabled(false);
        End.setEnabled(true);
    }
    public void Init() {
        board.Init();
        hand.Init();
        UIHelper.apply(draw, 0.15, 0,0.8,0,0.2,0,0.4,0,0,1);
        UIHelper.apply(drawCell, 0.15, 0,0.8,0,0.2,0,0.6,0,0,0);
        UIHelper.apply(End, 0.15, 0,0.8,0,0.1,0,0.5,0,0,0.5);
        UIHelper.apply(up, 0.45, 0, 0.5, 0, 0.05,0,0.02,0,0.5,0);
        UIHelper.apply(down, 0.45, 0, 0.5, 0, 0.03,0,0.98,0,0.5,1);
    };

    public void ResetDraw() {
        draw.setEnabled(true);
        drawCell.setEnabled(true);
        End.setEnabled(false);
    }

    public void Enable() {
        up.setVisible(true);
        down.setVisible(true);
        draw.setVisible(true);
        drawCell.setVisible(true);
        End.setVisible(true);
        draw.setEnabled(true);
        drawCell.setEnabled(true);
        hand.setVisible(true);
    }
}
