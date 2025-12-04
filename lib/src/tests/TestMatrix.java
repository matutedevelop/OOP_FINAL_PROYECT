package tests;




import java.util.Arrays;

import linearalgebra.Matrix;
import linearalgebra.SquareMatrix;
import linearalgebra.Vector;
import numerical.RootQR;

public class TestMatrix {
    public static void main(String[] args) {

        Matrix m = new Matrix(2, 4);
        System.out.println(m);
        
        double[][] valores = {{1.0 , 2.0, 3.0, 4.0} , {1.0 , 2.0,343.0,23.0}};

        Matrix m1 = new Matrix(valores);
        System.out.println(m1);

        System.out.println(m.sumar(m1));

        System.out.println(m1.transpuesta());

        System.out.println(m1.multiplicarEscalar(0));

        System.out.println(m1.multiplicarEscalar(2));

        System.out.println(m1.clone());

        SquareMatrix mEye = SquareMatrix.eyeMatrix(3);
        System.out.println(mEye);

        System.out.println(mEye.getDeterminante());

        double[][] val2 = {{1.0, -2.0,-1.0,3.0},{-1.0,3.0,-2.0,-2.0},{2.0,0.0,1.0,1.0},{1.0,-2.0,2.0,3.0}};
        SquareMatrix m2 = new SquareMatrix(val2);
        System.out.println(m2);
        System.out.println(m2.getDeterminante());
        System.out.println(m2.getInversa());
        System.out.println(m2.multiplicar(m2.getInversa()));

        double[][] val3 = {{1.0, 2.0, 3.0},{4.0,5.0,6.0},{7.0, 8.0, 9.0}};
        SquareMatrix m3 = new SquareMatrix(val3);

        System.out.println(m3);
        double[] eig = m3.eigenValues();
        System.out.println(Arrays.toString(eig));
        System.out.println(m3.eigenVectors());

        System.out.println(mEye.sumar(m3));

        double[] coef = {1.0, -5.0, 6.0};
        System.out.println(Arrays.toString(RootQR.roots(coef)));    
    }
}
