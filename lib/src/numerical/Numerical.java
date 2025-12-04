package numerical;

import java.util.function.Function;

import linearalgebra.Matrix;
import linearalgebra.SquareMatrix;

public interface Numerical {

    public static double NRroots(Double a, Function<Double, Double> f, Double x0) {

    Function<Double,Double> f_prime = diff(f);

        Function<Double,Double> new_f = x -> f.apply(x) - a;

        int max_iter = 10000;

        for (int i = 0; i < max_iter; i++) {

            if (f_prime.apply(x0) == 0) {

                throw new ArithmeticException("Divicion entre 0, pruebe otro valor inicial");
                
            }

            x0 = x0 - new_f.apply(x0)/f_prime.apply(x0);
            
        }

        return x0;

    }

    // public static Function<Vector, Double> diffv(Function<Vector<Double>, Double> f) {
    // return 0.0;
    // }

    public static Function<Double, Double> diff(Function<Double, Double> f) {

        double h = 0.0000001;

        Function<Double,Double> f_prime = x  -> (f.apply(x) - f.apply(x + h))/h;

        return f_prime;
    }



    public static double  integrate(Function<Double, Double> f, double a, double b) {


        double sum = 0;
        int n = 100000;
        double h = (b-a)/n;

        double[] x_s = new double[n];

        for (int i = 0; i < n; i++) {

            x_s[i] = a + i*h;
            
        }

        for (int i = 1; i < x_s.length; i++) {
            sum += h/2*(f.apply(x_s[i - 1]) + f.apply(x_s[i]));
        }



        return sum;
    }

    public static Matrix<Double> solveLinearEq(SquareMatrix<Double> coefMatrix,Matrix<Double> constants){

        if (constants.getNcols() != 1) {
            throw new IllegalArgumentException("La matriz de constantes tiene que ser de dimensiones nx1");
        }

        SquareMatrix<Double> invCoefMatrix = coefMatrix.getInversa();
        return invCoefMatrix.multiplicar(constants);


    }


}
