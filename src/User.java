import java.util.Map;
import java.util.Scanner;

public class User extends Player{
    Scanner scanner = new Scanner(System.in);

    public User(Map<Card, Integer> deck) {
        super(deck);
    }

    @Override
    void playTurn() {
        System.out.println("Choose What u gonna do");
        System.out.println("[1] - Draw");
        System.out.println("[2] - Place");
        Decidetion(scanner.nextInt());
    }

    private void Decidetion(int n) {
        switch (n) {
            case 1 :
                System.out.println("1");
                draw();
                show();
                break;
            case 2 :
                System.out.println("2");

                break;
            case 3 :
                System.out.println("3");
                break;
        }
    }
}
