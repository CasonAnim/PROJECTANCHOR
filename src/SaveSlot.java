import javax.swing.*;
import java.awt.*;

public class SaveSlot extends JPanel {
    private JLabel title = new JLabel("Save : N/A");
    private JLabel Desc = new JLabel("Stage : N/A");
    private JLabel reqpass = new JLabel("Require Password : N/A");
    private JButton load = new JButton("LOAD");
    private Font fontBOLD = new Font("Arial", Font.BOLD, 30);
    private Font fontLight = new Font("Arial", Font.PLAIN, 15);
    SaveSlot() {
        setLayout(null);
        title.setFont(fontBOLD);
        Desc.setFont(fontLight);
        reqpass.setFont(fontLight);
        load.setFont(fontBOLD);
        load.setHorizontalAlignment(JLabel.CENTER);
        load.addActionListener(e-> {
            System.out.println("LOAD " + title.getText());
        });
        add(title);
        add(Desc);
        add(reqpass);
        add(load);
        setBackground(Color.WHITE);
    }
    public void Init() {
         UIHelper.apply(title , 0.7,0,0,0,0.5,0,0,0);
         UIHelper.apply(Desc , 0.7,0,0,0,0.25,0,0.5,0);
         UIHelper.apply(reqpass , 0.7,0,0,0,0.25,0,0.75,0);
         UIHelper.apply(load,0.25,0,1,0,0.5,0,0.25,0,1,0);
    }

    public void setTitle(int index) {
        this.title.setText("Save : "+ index);
    }
}
