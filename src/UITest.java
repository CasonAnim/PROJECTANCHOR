import javax.swing.*;

public class UITest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ImageIcon cardframe = new ImageIcon("icon.png");
        JPanel panel = new JPanel();
        JLabel label = new JLabel("SADASD");
        frame.add(label);
        label.setIcon(cardframe);
        frame.setSize(850, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(cardframe.getImage());
    }

}
