public class AnchorInstance {
    String name;
    int selfHP;
    Anchor anchor;
    public AnchorInstance(Anchor anchor) {
        this.name = anchor.getName();
        this.selfHP = anchor.getHp();
        this.anchor =anchor;
    }

    public void ability(Board board) {
        anchor.ability(board);
    }

    public void setSelfHP(int selfHP) {
        this.selfHP = selfHP;
    }

    public int getMAXHP() {
        return anchor.getHp();
    }
    public int getSelfHP(){
        return selfHP;
    };

    public void takeDMG(int n) {
        if (n<0 || selfHP-n < 0) {
            selfHP=0;
        } else {
            selfHP-=n;
        }
    }
}
