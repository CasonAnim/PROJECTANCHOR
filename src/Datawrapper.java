import java.util.Map;

public class Datawrapper {
    int stage;
    boolean requirdPass;
    String pass = "";
    Map<String, Integer> deck;

    public Datawrapper(int stage, boolean requirdPass, String pass, Map<String, Integer> deck) {
        this.stage = stage;
        this.requirdPass = requirdPass;
        this.pass = pass;
        this.deck = deck;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void setRequirdPass(boolean requirdPass) {
        this.requirdPass = requirdPass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDeck(Map<String, Integer> deck) {
        this.deck = deck;
    }
    public boolean isComplete() {
        return stage != -1 && deck != null;
    }

    public Map<String, Integer> getDeck() {
        return deck;
    }

    public String getPass() {
        return pass;
    }

    public boolean isRequirdPass() {
        return requirdPass;
    }

    public int getStage() {
        return stage;
    }
}
