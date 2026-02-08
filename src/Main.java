public class Main {

    public static void main(String[] args) {
        InstanceCard a1 = new InstanceCard(CardOriginal.TEST_NO1);
        InstanceCard a2 = new InstanceCard(CardOriginal.TEST_NO2);
        InstanceCard a3 = new InstanceCard(CardOriginal.TEST_NO3);
        InstanceCard a4 = new InstanceCard(CardOriginal.TEST_NO4);
        Slot slot_a1 = new Slot();
        Board board = new Board();
        board.placeCard(a1, 0,true);
        board.placeCard(a2, 1,true);
        board.placeCard(a3, 2,true);
        board.placeCard(a4, 3,true);

        board.showBoard();



    }

}
