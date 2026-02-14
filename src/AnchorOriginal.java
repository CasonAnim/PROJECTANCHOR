public class AnchorOriginal {
    public static final Anchor anchor_1 = new Anchor("Anchor", 10) {
        @Override
        public void ability(Board board) {
            for (Slot slot : board.getEnemySlot()) {
                if (!slot.isEmpty()) {
                    slot.getCard().TakeDamage(1);
                    System.out.println("Shocked - " + slot.getCard().getName());
                }
            }
            board.updateDeadstate();
        }
    };
}
