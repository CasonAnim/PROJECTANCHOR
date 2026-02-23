import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class LoadSaveMenu extends JPanel {
    private JLabel Title = new JLabel("Load Save");
    private Font font = new Font("Arial", Font.BOLD, 75);
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panel);
    LoadSaveMenu() {
        setLayout(null);
        add(scrollPane);
        add(Title);
        scrollPane.setBackground(Color.BLACK);
        panel.setBackground(Color.BLACK);
        setBackground(Color.BLACK);
        Title.setFont(font);
        Title.setForeground(Color.WHITE);
        Title.setOpaque(false);
        Title.setHorizontalAlignment(JLabel.CENTER);
//        Title.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }
    public void Init() {
        UIHelper.apply(scrollPane, 0.65, 0,0.15,0,0.65,0,0.24,0);
        UIHelper.apply(Title, 1, 0,0,10,0.2,0,0.02,0);
        panel.setSize(scrollPane.getSize());
    }

    public void Clear() {
        panel.removeAll();
    }
    public void RenderSave(Datawrapper datawrapper , int index) {
        SaveSlot n = new SaveSlot();


        boolean isPassReq = datawrapper.isRequirdPass();
        String pass = datawrapper.pass;
        n.setTitle(index);
        n.setDesc(datawrapper.getStage());
        n.setReqpass(isPassReq);
        n.InjectData(datawrapper);
        panel.add(n);
        int parentW = 800;
        int parentH = 100;

        n.setSize(parentW, parentH); // <--- ต้องตบขนาดเข้าไปก่อน!
        n.setPreferredSize(new Dimension(parentW, parentH));
        n.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        System.out.println("W : " + n.getWidth());
        System.out.println("H : " + n.getHeight());
        n.Init();
    }
    public void RenderSave(int amountOfSave) {
        panel.removeAll();
        if (amountOfSave>0) {
            for (int i = 0; i < amountOfSave; i++) {
                SaveSlot n = new SaveSlot();
                n.setTitle(i+1);
                panel.add(n);
                int parentW = 800;
                int parentH = 100;

                n.setSize(parentW, parentH); // <--- ต้องตบขนาดเข้าไปก่อน!
                n.setPreferredSize(new Dimension(parentW, parentH));
                n.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                panel.add(Box.createRigidArea(new Dimension(0,20)));
                System.out.println("W : " + n.getWidth());
                System.out.println("H : " + n.getHeight());
                n.Init();
            }
        } else {
            System.out.println("Invalid Amout");
            System.out.println("No save");
        }

    }
}
