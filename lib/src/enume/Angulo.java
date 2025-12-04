package enume;


public enum Angulo {

    GRADOS(0),
    RADIANES(1);

    private final int val;

    private Angulo(int val) {
        this.val = val;
    }

    public int value() {
        return this.val;
    }

}
