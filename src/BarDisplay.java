import javax.swing.*;
import java.awt.*;

public class BarDisplay extends JPanel {
    private JPanel fill = new JPanel();
    private Font font = new Font("Arial", Font.BOLD, 15);
    private JLabel text = new JLabel();
    BarDisplay (Color color) {
        setLayout(null);
        fill.setBackground(color);
        add(fill);
        add(text);
        setBackground(new Color(79, 79, 79));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(font);
        text.setForeground(Color.WHITE);
        setComponentZOrder(text, 0);

    }
    public void setText(String text) {
        this.text.setText(text);
    }

    public void setFill(double scale) {
        UIHelper.apply(fill , scale , 0, 0,0,1,0,0,0);
        setComponentZOrder(text, 0);

    }

    public void Init() {
        UIHelper.apply(fill , 1 , 0, 0,0,1,0,0,0);
        UIHelper.apply(text , 1 , 0, 0.5,0,1,0,0.5,0,0.5,0.5);
        setComponentZOrder(text, 0);

    }
}
