import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class User extends Player{
    private List<Integer> tempAva;
    private int selec;
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
                board.printAvailableSlot(true);
                System.out.println("Choose Slot to place : ");
                selec = scanner.nextInt();
                selectSlot(selec);
                break;
            case 3 :
                System.out.println("3");
                break;
        }
    }

    private void selectSlot(int index) {
        tempAva = board.getIndexAvailableSlotList(true);

        if (tempAva.contains(index)) {
            System.out.println("Choose card");
            showHand();
            selectCard(scanner.nextInt());
        }
        tempAva = null;
    }

    private void selectCard(int index) {
        place(getHand().get(index), selec , true);
    }

}
