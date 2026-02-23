import java.util.HashMap;
import java.util.Map;

public class CardOriginal {
    public static final Card TEST_NO1 = new Card("Undefined", 10,4,0,"test01.png");
    public static final Card TEST_NO2 = new Card("Null Agent", 5,2,2,"test02.png");
    public static final Card Glitch = new Card("Glitch" ,2,1,0,"glitch.png");
    public static final Card CGUARD = new Card("Corrupt Guard" ,5,2,0,"cguard.png");
    public static final Card CELL = new Card("Cell", 1,0,0,"cell.png");
    public static final Card TEST_NO4 = new Card("Corrupted Agent", 3,2,1,"test04.png");
    public static final Card GUARD =  new Card("Riot Guard", 5,1,1,"riot.png");
    public static final Card JUGGERNAUT = new Card("Juggernaut", 5 , 3 , 2,"juggernaut.png");
    public static final Card RIFLEMAN = new Card("Rifleman", 2, 2,1,"riflman.png");
    public static final Card HAMMERBOT = new Card("Hammer bot", 3, 3,1,"hammerbot.png");
    public static final Card DASHOCKER = new Card("DaShocker", 3, 2,1,"dashocker.png");
    public static final Card GOD = new Card("UNDEFINED", 99999, 99999,0,"god.png");

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
