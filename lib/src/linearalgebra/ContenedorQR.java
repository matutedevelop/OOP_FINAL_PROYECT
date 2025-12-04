package linearalgebra;

public class ContenedorQR {

    public Matrix Q;
    public Matrix R;

    public ContenedorQR(Matrix Q, Matrix R) {
        this.Q = Q;
        this.R = R;
    }

    public Matrix getQ() {
        return Q;
    }

    public Matrix getR() {
        return R;
    }

}
