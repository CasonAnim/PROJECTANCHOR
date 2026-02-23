import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField pass = new JTextField();
    private JLabel title = new JLabel("PLACEHOLDER");
    private Datawrapper data;
    private String currentPassword;
    private JButton confirm_save = new JButton("Confirm");
    private ActionListener save = e -> {
        MainUI.setSave(pass.getText());
        GlobalListenerManger.getInstance().FireRemoteEvent(3,1);
    };
    private ActionListener load = e -> {
        String insert = this.pass.getText();
        System.out.println("InsertPass");
        if (data.isRequirdPass()){
            if (insert.equals(data.getPass())){
                GlobalListenerManger.getInstance().FireRemoteEvent(6, data);
                System.out.println("Correct Pass");
            } else {
                System.out.println("Incorrect Pass");
            }
        } else {
            GlobalListenerManger.getInstance().FireRemoteEvent(6, data);
        }
    };
    LoginPanel() {
        GlobalListenerManger.getInstance().OnRemoteEvent((Channel, data1) -> {
            if (Channel==5) {
                if (data1 instanceof Datawrapper) {
                    data =  (Datawrapper) data1;
                    System.out.println("Set Data Success");
                    System.out.println("Save Stage : "+data.getStage());
                    System.out.println("Save Password : "+data.getPass());
                }
            }
        });

        setLayout(null);
        setBackground(Color.red);
        add(pass);
        add(title);
        add(confirm_save);
        title.setHorizontalAlignment(JLabel.CENTER);

        confirm_save.addActionListener(e -> {

        });

    }
    public void setsave() {
        this.pass.setText("");
        confirm_save.removeActionListener(load);
        confirm_save.addActionListener(save);
        title.setText("Set Password");
    }
    public void setLoad(String pass) {
        this.pass.setText("");
        confirm_save.removeActionListener(save);
        confirm_save.addActionListener(load);
        title.setText("Insert Password");


    }

    public void BoundingData() {

    }
    public void Init() {
        UIHelper.apply(pass, 0.6,0,0.5,0,0.3,0,0.6,0,0.5,1);
        UIHelper.apply(title, 0.6,0,0.5,0,0.2,0,0.3,0,0.5,1);
        UIHelper.apply(confirm_save, 0.3,0,0.5,0,0.1,0,0.75,0,0.5,0.5);
    }
}
