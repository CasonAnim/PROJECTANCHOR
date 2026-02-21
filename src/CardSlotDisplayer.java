import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardSlotDisplayer extends JPanel {
    private SlotDisplay plrSlot = new SlotDisplay();
    private SlotDisplay enemySlot = new SlotDisplay();
    private BarDisplay Health = new BarDisplay(new Color(121, 88, 184));
    private BarDisplay EnimHealth = new BarDisplay(new Color(0, 230, 255));

    CardSlotDisplayer() {
        this.setBackground(new Color(69, 57, 97));
        this.setLayout(null);
        this.add(plrSlot);
        this.add(enemySlot);
        this.add(Health);
        this.add(EnimHealth);

        GlobalListenerManger.getInstance().onAfterBattleListener((e,v) -> {
            double eD = (double) e /10;
            if (v) {
                Health.setFill(eD);
                System.out.println("Player Health : " + e);
            }else {

                EnimHealth.setFill(1-eD);
                System.out.println("Enemy Health : " + e);
            }

        });

        GlobalListenerManger.getInstance().onOnPlacePaint((card, index, isPlayer) -> {
            if (isPlayer) {
                plrSlot.setCardDisplay(card, index);
            } else {
                enemySlot.setCardDisplay(card, index);
            }
        });

        GlobalListenerManger.getInstance().onsacrificeplaceable(e -> {
            System.out.print("These Index have been brand as sacrificed : ");
            for (InstanceCard card : e) {
                System.out.print("["+e.indexOf(card)+"]");
            }
            System.out.println("");
            ArrayList<FramewCrad> list = plrSlot.getList().getList();
            for (FramewCrad i : list) {

//                System.out.println(list.indexOf(i) + " Placable || Sac brand? : " + i.isSac() + " || is Empty "+ i.isEmpty);
                if (i.isSac() || i.isEmpty) {
                    System.out.println(list.indexOf(i) + " Placable || Sac brand? : " + i.isSac() + " || is Empty "+ i.isEmpty);
                    i.setPlacable();
                } else {
                    if (i.isEmpty) {
                        i.clearplaceable();
                    } else {
                        i.clearplaceable();
                        i.removeBoarder();
                    }
                }
            }
        });

        GlobalListenerManger.getInstance().onSacrificeable(e -> {
            for (int i : e) {
                plrSlot.sacrificeable(i);
            }
        });
        GlobalListenerManger.getInstance().onIsSlotEmptyRequest(e -> {
            for (int i : e) {
                plrSlot.Available(i);
            }
        });
        GlobalListenerManger.getInstance().ontakeDMG((index,card ,isPlayerSide) -> {
                String HP = String.valueOf(card.getSelfHP());
                System.out.println("Index " + index +" Damaged || isPlayerside ? : " + isPlayerSide + "|| Name : " + card.getName() + "" +
                        "\t HP - " +card.getSelfHP());

                if  (isPlayerSide) {
                    plrSlot.getList().getData(index).setHpText(HP);
                    System.out.println("player get attack!");
                } else {
                    enemySlot.getList().getData(index).setHpText(HP);
                    System.out.println("Enemy get attack!");
                }
        });


        GlobalListenerManger.getInstance().onDeath((index, isPlayerSide) -> {
//            System.out.println("index : " + index + " || Playerside : " + isPlayerSide);

            if (isPlayerSide) {
                plrSlot.getList().getData(index).setEmpty();
            } else {
                enemySlot.getList().getData(index).setEmpty();
            }
        });
        GlobalListenerManger.getInstance().onDisableListener(() -> {
            for (FramewCrad crad : plrSlot.getList().getList()) {
                if (crad.isEmpty) {
                    crad.clearplaceable();
                }
            }
        });

        GlobalListenerManger.getInstance().onDeSlectListener(() -> {
            for (FramewCrad i : plrSlot.getList().getList()) {
                if (i.isEmpty) {
                    i.clearplaceable();
                } else if(!i.isEmpty) {
                    i.placed();
                }
            }
        });

    }
    public void Init() {
        UIHelper.apply(this,1,0,0,0,1,0,0,0);
        UIHelper.apply(plrSlot,0.6,0,0.5,0,0.35,0,0.9,0,0.5,1);
        UIHelper.apply(enemySlot,0.6,0,0.5,0,0.35,0,0.02,0,0.5,0);
        UIHelper.apply(Health,0.5,0,0.5,0,0.05,0,0.4,0,0.5,0);
        UIHelper.apply(EnimHealth,0.5,0,0.5,0,0.05,0,0.47,0,0.5,0);
        enemySlot.InitEnemy();
        plrSlot.Init();
        plrSlot.setEmpty();
        enemySlot.setEmpty();
        Health.Init();
        EnimHealth.Init();
        EnimHealth.setFill(0);
        Health.setText("STABILITY");
        EnimHealth.setText("BEACON FILLED");
    }



//    public void updateDisplay() {
//        for (int i = 0; i < Board.SLOT_SIZE; i++) {
//            UIHelper.apply(list.get(i), 0.2,0,space * i , 0,1,0,0,0);
//        }
//    }
//    public void setSlot1(InstanceCard card) {
//
//    }




}
