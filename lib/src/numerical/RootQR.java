package numerical;

import linearalgebra.SquareMatrix;

public class RootQR {
    
    public static double[] roots(double[] coeficientes) {
        int n = coeficientes.length - 1;
    
        if (n < 1) throw new IllegalArgumentException("Se requieren al menos 2 coeficientes.");

        SquareMatrix A = new SquareMatrix(n);

        double p0 = coeficientes[0];
        
        for (int j = 0; j < n; j++) {
            // Llenamos la primera fila con los coeficientes normalizados y negados
            // Empezamos desde coeficientes[1] hasta el final
            double val = -coeficientes[j + 1] / p0;
            A.setValue(0, j, val);
        }


        for (int i = 1; i < n; i++) {
            A.setValue(i, i - 1, 1.0);
        }


        return A.eigenValues();
    }




}
