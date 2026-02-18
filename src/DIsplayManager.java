public class DIsplayManager {
    CardListener listener;

    public void setListener(CardListener listener) {
        this.listener = listener;
    }
    public void paint(FramewCrad canvas, InstanceCard target) {
        System.out.println("เปลี่ยนจากปลายพู่กัน");
        if (listener != null) {
            listener.ondisplay(canvas, target);
        }
    }
}
