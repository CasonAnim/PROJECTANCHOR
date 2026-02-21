import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PanelCollector<T extends JPanel> {
    private List<T> list;
    PanelCollector() {
        list = new ArrayList<>();
    }

    public void push(T item) {
        list.add(item);
    }

    public T getData(int index) {
        return list.get(index);
    }

    public List<T> getList() {
        return list;
    }
}
