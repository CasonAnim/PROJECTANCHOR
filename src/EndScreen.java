import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {
    private JLabel txt = new JLabel("PLACEHOLDER");
    private JButton Continue = new JButton("Continue");
    private Font font = new Font("Arial", Font.BOLD, 75);
    EndScreen() {
        txt.setFont(font);
        txt.setOpaque(false);
        txt.setForeground(Color.WHITE);
        txt.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        txt.setHorizontalAlignment(JLabel.CENTER);
        setBackground(new Color(43,43,43));
        setLayout(null);
        add(txt);


    }
    public void Init() {
        UIHelper.apply(txt, 0.8,0,0.5,0,0.2,0,0.2,0,0.5,0.5);

    }

    public void setTxt(String txt) {
        this.txt.setText(txt);
    }
}
