import javax.swing.*;
import java.awt.*;

public class ITHINKTHISISMYFINALMYFROM extends JFrame {
    MainUI mainUI =new MainUI();
    ITHINKTHISISMYFINALMYFROM() {
        this.setSize(1280,720);
        setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("PROJECT:ANCHOR");
        add(mainUI);
        UIHelper.apply(mainUI,1,0,0,0,1,0,0,0);
        mainUI.InitMyself();
    }
}
