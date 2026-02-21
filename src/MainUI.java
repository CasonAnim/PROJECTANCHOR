import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MainUI extends JPanel {
    UIMainFrame mainFrame = new UIMainFrame();
    GameMenu gameMenu = new GameMenu();
    LoadSaveMenu loadSaveMenu = new LoadSaveMenu();
    MapUI mapUI = new MapUI();
    JPanel panel = new JPanel();
    JButton back = new JButton("<");
    JButton save = new JButton("Save");
    JButton backtoMenu = new JButton("Back to menu");
    List<JPanel> Comppy = new ArrayList<>();
    MainUI() {
        KeyStroke PressESC = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        InputMap map = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        Action action1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.isEnabled()){
                    System.out.println("ON/OFF");
                    if (mainFrame.isVisible()) {
                        mainFrame.setVisible(false);
                        panel.setVisible(true);
                    } else {
                        mainFrame.setVisible(true);
                        panel.setVisible(false);
                    }
                } else {
                    Enable(gameMenu);
                    panel.setVisible(false);
                    back.setVisible(false);
                }
            }
        };
        panel.setBackground(new Color(89, 89, 89));
        panel.add(save);
        panel.add(backtoMenu);
        panel.setLayout(null);
        backtoMenu.addActionListener(e -> {
            Enable(gameMenu);
            mainFrame.setEnabled(false);
            panel.setVisible(false);
        });


        map.put(PressESC, "KEY");
        actionMap.put("KEY", action1);


        setLayout(null);

        Comppy.add(mainFrame);
        Comppy.add(gameMenu);
        Comppy.add(loadSaveMenu);
        Comppy.add(mapUI);
        back.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        back.setForeground(Color.WHITE);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setBackground(Color.BLACK);
        panel.setBackground(Color.WHITE);
        add(back);
        add(panel);
        loadSaveMenu.RenderSave(15);
        back.setVisible(false);
        panel.setVisible(false);

        back.addActionListener(e -> {
            Enable(gameMenu);
            back.setVisible(false);
        });



        GlobalListenerManger.getInstance().onUiListener(e -> {
            if (e==0) {
                Enable(mapUI);
                back.setVisible(true);
            } else if (e==1) {
                Enable(loadSaveMenu);
                back.setVisible(true);
            } else if (e==2) {
                Enable(mainFrame);
                back.setVisible(false);
            }
        });


    }

    private void InitComp() {
        for (int i = 0; i < Comppy.size(); i++) {
            add(Comppy.get(i));
            UIHelper.apply(Comppy.get(i),1,0,0,0,1,0,0,0);
            System.out.println("Register : " + Comppy.get(i) + "W : " +Comppy.get(i).getWidth()+ " || H : " +Comppy.get(i).getHeight());
        }
        Disable();
    }

    private void Disable(JPanel target) {
        target.setVisible(false);
        target.setEnabled(false);
    }
    private void Disable() {
        for (JPanel comp : Comppy) {
            comp.setVisible(false);
            comp.setEnabled(false);
        }
    }

    public void InitMyself() {
        InitComp();
        mainFrame.Init();
        gameMenu.Init();
        loadSaveMenu.Init();
        mapUI.Init();
        Enable(gameMenu);
        UIHelper.apply(back, 0.07,0,0.05,0,0.09,0,0.05,0);
        UIHelper.apply(panel, 0.5,0,0.5,0,0.5,0,0.5,0,0.5,0.5);
        UIHelper.apply(backtoMenu, 0.75,0,0.5,0,0.25,0,0.25,0,0.5,0.5);
        UIHelper.apply(save, 0.75,0,0.5,0,0.25,0,0.75,0,0.5,0.5);
    }

    private void Enable(JPanel target) {
        for (JPanel comp : Comppy) {
            comp.setVisible(false);
            comp.setEnabled(false);
        }
        target.setVisible(true);
        target.setEnabled(true);
    }
}
