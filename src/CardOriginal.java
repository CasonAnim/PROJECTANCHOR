import java.util.HashMap;
import java.util.Map;

public class CardOriginal {
    public static final Card TEST_NO1 = new Card("Undefined", 10,4,0);
    public static final Card TEST_NO2 = new Card("Null Agent", 5,2,2);
    public static final Card Glitch = new Card("Glitch" ,2,1,0);
    public static final Card CGUARD = new Card("Corrupt Guard" ,5,2,0);
    public static final Card CELL = new Card("Cell", 1,0,0);
    public static final Card TEST_NO4 = new Card("Corrupted Agent", 3,2,1);
    public static final Card GUARD =  new Card("Riot Guard", 5,1,1);
    public static final Card JUGGERNAUT = new Card("Juggernaut", 5 , 3 , 0);
    public static final Card RIFLEMAN = new Card("Rifleman", 2, 2,1);
    public static final Card HAMMERBOT = new Card("Hammer bot", 3, 3,1);
    public static final Card DASHOCKER = new Card("DaShocker", 3, 2,1);
    public static final Card GOD = new Card("UNDEFINED", 99999, 99999,0);

    private static final Map<String, Card> REGISTRY = new HashMap<>();

    static {
        REGISTRY.put(TEST_NO1.getName(),TEST_NO1);
        REGISTRY.put(TEST_NO2.getName(),TEST_NO2);
        REGISTRY.put(Glitch.getName(), Glitch);
        REGISTRY.put(CGUARD.getName() , CGUARD);
        REGISTRY.put(CELL.getName() , CELL);
        REGISTRY.put(TEST_NO4.getName() ,TEST_NO4);
        REGISTRY.put(GUARD.getName() , GUARD);
        REGISTRY.put(JUGGERNAUT.getName() , JUGGERNAUT);
        REGISTRY.put(RIFLEMAN.getName() , RIFLEMAN);
        REGISTRY.put(HAMMERBOT.getName() , HAMMERBOT);
        REGISTRY.put(DASHOCKER.getName() , DASHOCKER);
        REGISTRY.put(GOD.getName() , GOD);
    }

    public static Card findCard(String name) {
        return REGISTRY.get(name);
    }

}
