package tests;


import java.util.Arrays;
import java.util.Vector;

import linearalgebra.Matrix;
import linearalgebra.SquareMatrix;

public class TestMatrix {
    public static void main(String[] args) {

        Matrix<Integer> m = new Matrix<>(2, 4);
        System.out.println(m);
        
        Integer[][] valores = {{1 , 2, 3, 4} , {1 , 2,343,23}};

        Matrix<Integer> m1 = new Matrix<>(valores);
        System.out.println(m1);

        System.out.println(m.sumar(m1));

        System.out.println(m1.transpuesta());

        System.out.println(m1.multiplicarEscalar(0));

        System.out.println(m1.multiplicarEscalar(2));

        System.out.println(m1.clone());

        SquareMatrix<Double> mEye = SquareMatrix.eyeMatrix(50);
        System.out.println(mEye);

        System.out.println(mEye.getDeterminante());

        Double[][] val2 = {{1.0, -2.0,-1.0,3.0},{-1.0,3.0,-2.0,-2.0},{2.0,0.0,1.0,1.0},{1.0,-2.0,2.0,3.0}};
        SquareMatrix<Double> m2 = new SquareMatrix<>(val2);
        System.out.println(m2);
        System.out.println(m2.getDeterminante());
        System.out.println(m2.getInversa());
        System.out.println(m2.multiplicar(m2.getInversa()));

        Double[][] val3 = {{1.0, 2.0, 3.0},{3.0,2.0,1.0},{2.0, 2.0, 2.0}};
        SquareMatrix<Double> m3 = new SquareMatrix<>(val3);

        System.out.println(m3);
        double[] eig = m3.eigenValues();
        System.out.println(Arrays.toString(eig));

    }
}
