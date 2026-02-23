import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainUI extends JPanel {
    UIMainFrame mainFrame = new UIMainFrame();
    GameMenu gameMenu = new GameMenu();
    LoadSaveMenu loadSaveMenu = new LoadSaveMenu();
    MapUI mapUI = new MapUI();
    JPanel panel = new JPanel();
    LoginPanel loginPanel = new LoginPanel();
    JButton back = new JButton("<");
    JButton save = new JButton("Save");
    JButton backtoMenu = new JButton("Back to menu");
    List<JPanel> Comppy = new ArrayList<>();
    boolean gameStart = false;

    MainUI() {
        KeyStroke PressESC = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        InputMap map = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();
        setBackground(Color.BLACK);
        Action action1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.isEnabled()) {
                    System.out.println("ON/OFF");
                    if (mainFrame.isVisible()) {
                        mainFrame.setVisible(false);
                        panel.setVisible(true);
                    } else {

                        mainFrame.setVisible(true);
                        panel.setVisible(false);
                    }
                } else {
                    if (mapUI.getCurrentStage() == 0 && !loginPanel.isVisible()) {
                        Enable(gameMenu);
                        panel.setVisible(false);
                        back.setVisible(false);
                    } else {
                        if (gameStart) {
                            if (mapUI.isVisible()) {
                                System.out.println("CASE 1.1");
                            } else {
                                Enable(mainFrame);
                                loginPanel.setVisible(false);
                                panel.setVisible(false);
                            }
                        }
                        else {
                            Enable(gameMenu);
                            loginPanel.setVisible(false);
                            back.setVisible(false);
                            System.out.println("CASE 2");
                        }
                    }

                }
            }
        };
        panel.setBackground(new Color(89, 89, 89));
        add(loginPanel);
        loginPanel.setVisible(false);
        panel.add(save);
        panel.add(backtoMenu);
        panel.setLayout(null);
        backtoMenu.addActionListener(e -> {
            Enable(gameMenu);
            mainFrame.setEnabled(false);
            panel.setVisible(false);
            gameStart = false;
        });

        save.addActionListener(e -> {
//            GlobalListenerManger.getInstance().fireUiListener(19);
//            setSave();
            panel.setVisible(false);
            loginPanel.setVisible(true);
            loginPanel.setsave();
            Disable(mainFrame);
//            Enable(gameMenu);
            System.out.println("Stage : " + mapUI.getCurrentStage());
//            mapUI.setCurrentStage(0);
        });


        map.put(PressESC, "KEY");
        actionMap.put("KEY", action1);


        setLayout(null);

        Comppy.add(mainFrame);
        Comppy.add(gameMenu);
        Comppy.add(loadSaveMenu);
        Comppy.add(mapUI);

        back.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        back.setForeground(Color.WHITE);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setBackground(Color.BLACK);
        panel.setBackground(Color.WHITE);
        add(back);
        add(panel);

        back.setVisible(false);
        panel.setVisible(false);

        back.addActionListener(e -> {
            Enable(gameMenu);
            back.setVisible(false);
            loginPanel.setVisible(false);
        });


        GlobalListenerManger.getInstance().OnRemoteEvent(((Channel, data) -> {
            if (Channel == 3) {
                if ((int) data == 1) {
                    Enable(gameMenu);
                    gameStart = false;
                    loginPanel.setVisible(false);
                }
            } else if (Channel == 4) {
                Enable(mapUI);
                mapUI.setMarker(mapUI.getCurrentStage());
            } else if (Channel == 5) {
                Disable(loadSaveMenu);
                loginPanel.setVisible(true);
                loginPanel.setLoad(String.valueOf(data));
                System.out.println(data);
            } else if (Channel == 6) {
                if (data instanceof Datawrapper) {

                    Datawrapper playerData = (Datawrapper) data;
                    System.out.println("[MAINUI:LOAD:CHANNEL - 6] - Loaded - " + playerData);
                    loginPanel.setVisible(false);
                    gameStart = true;
                    mapUI.setMarker(playerData.getStage());
                    Map<Card, Integer> deck = new HashMap<>();

                    playerData.getDeck().forEach((String, Integer) ->
                            {
                                Card card = CardOriginal.findCard(String);
                                if (card != null) {
                                    deck.put(card,Integer);
                                    System.out.println("[MAINUI:LOAD:CHANNEL - 6] - Added " + String + " Successfully");

                                } else {
                                    System.out.println("[MAINUI:LOAD:CHANNEL - 6] - Not founded" + String);
                                }
                            }
                    );
                    mapUI.setCurrentStage(playerData.getStage());
                    mapUI.setLoadfromsave(true);
                    mapUI.setDeck(deck);
                    Enable(mapUI);
                    back.setVisible(false);
                }
            }
        }));

        GlobalListenerManger.getInstance().onUiListener(e -> {
            if (e == 0) {
                Enable(mapUI);
                back.setVisible(true);
                gameStart = true;
            } else if (e == 1) {
                Enable(loadSaveMenu);
                back.setVisible(true);
            } else if (e == 2) {
                Enable(mainFrame);
                back.setVisible(false);
            } else if (e == 17) {
                List<String> list = Utilities.getSaveList();
                int listSize = list.size();
                loadSaveMenu.Clear();
                for (String i : list) {
                    System.out.println(i);
                    Datawrapper n = Utilities.Load(i);
                    loadSaveMenu.RenderSave(n, list.indexOf(i));
                }

            }
        });


    }

    private void InitComp() {
        for (int i = 0; i < Comppy.size(); i++) {
            add(Comppy.get(i));
            UIHelper.apply(Comppy.get(i), 1, 0, 0, 0, 1, 0, 0, 0);
            System.out.println("Register : " + Comppy.get(i) + "W : " + Comppy.get(i).getWidth() + " || H : " + Comppy.get(i).getHeight());
        }
        Disable();
    }

    public static void setSave(String password) {
        Datawrapper data = new Datawrapper(-1, false, "", null);
        System.out.println("IsComplete : " + data.isComplete());
        if (password.isEmpty()) {
            data.setRequirdPass(false);
        } else {
            data.setPass(password);
            data.setRequirdPass(true);
        }
        GlobalListenerManger.getInstance().OnRemoteEvent(
                (Channel, data1) -> {
                    if (Channel == 1) {
                        if (data1 instanceof Integer) {
                            data.setStage((int) data1);
                            System.out.println("Stage : " + data1);
                        }
                        if (data1 instanceof List) {

                            List<InstanceCard> listdeck = (List<InstanceCard>) data1;
                            System.out.println("[LISTDECK] " + listdeck);
                            Map<String, Integer> decky = (listdeck).stream().collect(
                                    Collectors.groupingBy(card -> card.getCard().getName()
                                            , Collectors.collectingAndThen(
                                                    Collectors.counting(),
                                                    Long::intValue
                                            )
                                    )
                            );
//                            System.out.println(decky);
                            data.setDeck(decky);

                        }
                    }
                }
        );

        GlobalListenerManger.getInstance().FireRemoteEvent(0, null);
        System.out.println("[FROM MAINUI] : " + data.getDeck());
        if (data.isComplete()) {
            System.err.println("Save Success!");
            Utilities.save(data);
        } else {
            System.err.println("Failed");
        }


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
        UIHelper.apply(back, 0.07, 0, 0.05, 0, 0.09, 0, 0.05, 0);
        UIHelper.apply(panel, 0.5, 0, 0.5, 0, 0.5, 0, 0.5, 0, 0.5, 0.5);
        UIHelper.apply(backtoMenu, 0.75, 0, 0.5, 0, 0.25, 0, 0.25, 0, 0.5, 0.5);
        UIHelper.apply(save, 0.75, 0, 0.5, 0, 0.25, 0, 0.75, 0, 0.5, 0.5);
        UIHelper.apply(loginPanel, 0.5, 0, 0.5, 0, 0.5, 0, 0.5, 0, 0.5, 0.5);
        loginPanel.Init();
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
