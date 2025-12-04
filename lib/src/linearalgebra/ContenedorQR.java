package linearalgebra;

public class ContenedorQR {

    public Matrix<Double> Q;
    public Matrix<Double> R;

    public ContenedorQR(Matrix<Double> Q, Matrix<Double> R) {
        this.Q = Q;
        this.R = R;
    }

    public Matrix<Double> getQ() {
        return Q;
    }

    public Matrix<Double> getR() {
        return R;
    }

}
