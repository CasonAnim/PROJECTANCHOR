import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Collector<T extends JPanel> {
    private T data;
    private ArrayList<T> list;


    Collector() {
        this.list = new ArrayList<>();
    }

    public void push(T item) {
        list.add(item);
    }


    public T getData(int index) {
        return list.get(index);
    }

    public ArrayList<T> getList() {
        return list;
    }

}
