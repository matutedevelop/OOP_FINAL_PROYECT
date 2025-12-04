package app;

import java.util.Arrays;

import linearalgebra.Matrix;
import linearalgebra.SquareMatrix;

public class PCA {

    public static double mean(double[] X) {

        double sum = 0;

        for (double d : X) {
            sum += d;
        }

        return sum / X.length;
    }

    public static double covar_cols(double[] X, double[] Y) {

        if (X.length != Y.length) {

            throw new IllegalArgumentException("Las columnas no son de la misma longitud");
        }

        double sum = 0;

        int n = X.length;

        double meanX = mean(X);
        double meanY = mean(Y);
        // System.out.println("////////////");
        // System.out.println(meanY);
        // System.out.println(meanX);

        for (int i = 0; i < n; i++) {
            sum += (X[i] - meanX) * (Y[i] - meanY);
        }

        return sum / (n - 1);

    }

    public static SquareMatrix covarMatrix(Matrix A) {

        int ncols = A.getNcols();

        SquareMatrix covarMat = new SquareMatrix(ncols);

        for (int i = 0; i < ncols; i++) {
            for (int j = 0; j < ncols; j++) {

                Double covar = covar_cols(A.getColumn(i), A.getColumn(j));
                covarMat.setValue(i, j, covar);

            }

        }

        return covarMat;

    }

    public static void main(String[] args) {

        // Matrix<Double> A = new Matrix<Double>(12, 2);
        //
        // for (int i = 0; i < 12; i++) {
        //
        //     for (int j = 0; j < 2; j++) {
        //
        //         double val = i + j;
        //         A.setValue(i, j, val);
        //
        //     }
        // }

        double[][] data = {

                { 4.58304446, 4.58304446 },
                { 4.18649674, 4.18649674 },
                { 4.68455182, 4.68455182 },
                { 5.29780225, 5.29780225 },
                { 5.63873004, 5.63873004 },
                { 4.68391033, 4.68391033 },
                { 5.59344871, 5.59344871 },
                { 5.10408481, 5.10408481 },
                { 4.98992128, 4.98992128 },
                { 5.85089917, 5.85089917 },
                { 4.55156392, 4.55156392 },
                { 5.95059776, 5.95059776 },
                { 4.58986748, 4.58986748 },
                { 7.41339993, 7.41339993 },
                { 5.70913445, 5.70913445 },
                { 5.56975073, 5.56975073 },
                { 3.73346196, 3.73346196 },
                { 5.55688174, 5.55688174 },
                { 4.71630392, 4.71630392 },
                { 4.35080569, 4.35080569 },
                { 5.50063208, 5.50063208 },
                { 5.89959163, 5.89959163 },
                { 4.69358398, 4.69358398 },
                { 4.77330821, 4.77330821 },
                { 5.77565207, 5.77565207 },
                { 5.26358689, 5.26358689 },
                { 4.75307442, 4.75307442 },
                { 4.40773471, 4.40773471 },
                { 6.66707095, 6.66707095 },
                { 3.47045899, 3.47045899 },
                { 5.32265056, 5.32265056 },
                { 6.45583019, 6.45583019 },
                { 4.36291121, 4.36291121 },
                { 4.44770839, 4.44770839 },
                { 5.81267983, 5.81267983 },
                { 6.09577002, 6.09577002 },
                { 3.89422304, 3.89422304 },
                { 6.32220637, 6.32220637 },
                { 6.82766229, 6.82766229 },
                { 7.31800523, 7.31800523 },
                { -3.13689022, -3.13689022 },
                { -4.17733652, -4.17733652 },
                { -4.26945421, -4.26945421 },
                { -4.87316546, -4.87316546 },
                { -3.0515124, -3.0515124 },
                { -3.22492891, -3.22492891 },
                { -1.42958651, -1.42958651 },
                { -3.12157204, -3.12157204 },
                { -2.1323172, -2.1323172 },
                { -2.99811091, -2.99811091 },
                { -3.53246285, -3.53246285 },
                { -4.52667512, -4.52667512 },
                { -4.38065648, -4.38065648 },
                { -1.97900159, -1.97900159 },
                { -5.14770324, -5.14770324 },
                { -3.44023878, -3.44023878 },
                { -1.16206408, -1.16206408 },
                { -3.32481868, -3.32481868 },
                { -1.31459445, -1.31459445 },
                { -2.43444551, - 2.43444551 }

        };

        Matrix B = new Matrix(data);

        System.out.println(B);

        SquareMatrix covarB = covarMatrix(B);

        System.out.println(covarB);

        System.out.println("eigenvalues");

        System.out.println(Arrays.toString(covarB.eigenValues()));
        System.out.println("eigenvectors");

        System.out.println(covarB.eigenVectors());



        double[] eigenVector = covarB.eigenVectors().getColumn(0);

        Matrix projectionMatrix = new Matrix(1,2);
        projectionMatrix.setValue(0, 0, eigenVector[0]);
        projectionMatrix.setValue(0, 1, eigenVector[1]);

        System.out.println("Matriz de proyeccion");

        System.out.println(projectionMatrix);


        Matrix projectedData = projectionMatrix.multiplicar(B.transpuesta());

        System.out.println(projectedData);

    }

}
