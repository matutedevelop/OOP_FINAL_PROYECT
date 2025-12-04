package linearalgebra;
import numerical.QR;

public class SquareMatrix extends Matrix {

    // atributos
    private int dimension;

    // Constructores 
    public SquareMatrix(int dimension) {
        // Llamamos al constructor padre con filas=dimension y columnas=dimension
        super(dimension, dimension); 
        this.dimension = dimension;
    }

    // Constructor 2: Recibe datos ya existentes
    public SquareMatrix(double[][] values) {
        super(values);
        // Validación extra: Debe ser cuadrada
        if (this.getNrows() != this.getNcols()) {
            throw new IllegalArgumentException("Los datos no forman una matriz cuadrada.");
        }

        this.dimension = this.getNrows();
        
    }

    public int getDimension() {
        return dimension;
    }

    // Metodos que solo tiene la matriz cuadrada

    public static SquareMatrix eyeMatrix(int dimension) {

        SquareMatrix matriz = new SquareMatrix(dimension);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == j) {
                    matriz.setValue(i, j, 1.0);
                }
                else {
                    matriz.setValue(i, j, 0.0);
                }
            }
        }

        return matriz;
    }

    public double traza() {
        double suma = 0;
        for (int i = 0; i < this.dimension; i++) {
            // Usamos doubleValue() para tener precisión aritmética
            suma += this.values[i][i];
        }
        return suma;
    }

    public boolean esSimetrica() {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < i; j++) { // Solo recorremos el triángulo inferior
                double valA = this.values[i][j];
                double valB = this.values[j][i];
                
                if (valA != valB) return false;
            }
        }
        return true;
    }

    public double getDeterminante() {
        return determinanteRecursivo(this.values);
    }

    private double determinanteRecursivo(double[][] matrizActual) {
        int n = matrizActual.length;
        // Caso Matriz 1x1
        if (n == 1) {
            return matrizActual[0][0];
        }

        // Caso Matriz 2x2 (
        if (n == 2) {
            double a = matrizActual[0][0];
            double b = matrizActual[0][1];
            double c = matrizActual[1][0];
            double d = matrizActual[1][1];
            return (a * d) - (b * c);
        }

        // Caso Recursivo: Expansión de Laplace
        double determinante = 0;
        for (int c = 0; c < n; c++) {
            // Alternancia de signos (+ - + -)
            double signo = (c % 2 == 0) ? 1.0 : -1.0;
            double valorActual = matrizActual[0][c];
            
            // Si el valor es 0, saltamos el cálculo para optimizar
            if (valorActual == 0) continue;

            double[][] subMatriz = crearSubMatriz(matrizActual, 0, c);
            determinante += signo * valorActual * determinanteRecursivo(subMatriz);
        }

        return determinante;
    }

    private double[][] crearSubMatriz(double[][] original, int filaAExcluir, int colAExcluir) {
        int n = original.length;
        double[][] nueva = new double[n-1][n-1];
        
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (i == filaAExcluir) continue;
            r++;
            int c = -1;
            for (int j = 0; j < n; j++) {
                if (j == colAExcluir) continue;
                nueva[r][++c] = original[i][j];
            }
        }
        return nueva;
    }

    public SquareMatrix getInversa() {

        double det = this.getDeterminante();

        // Si el determinante es 0, matemáticamente no existe inversa
        if (Math.abs(det) < 1e-10) { 
            throw new ArithmeticException("La matriz es singular (Determinante = 0), no tiene inversa.");
        }

        int n = this.getNrows();
        SquareMatrix matrizInversa = new SquareMatrix(n);

        // Caso  Matriz de 1x1
        if (n == 1) {
            matrizInversa.setValue(0, 0, 1.0 / det);
            return matrizInversa;
        }

        // Construir la Matriz Adjunta (Cofactores Transpuestos) / Determinante
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // btener la submatriz eliminando fila i y columna j
                double[][] subMatriz = crearSubMatriz(this.values, i, j);

                // Calcular el determinante de esa submatriz (Menor)
                double detMenor = determinanteRecursivo(subMatriz);

                // Determinar el signo del cofactor (+ - + -)
                double signo = ((i + j) % 2 == 0) ? 1.0 : -1.0;
                double cofactor = signo * detMenor;

                matrizInversa.setValue(j, i, cofactor / det);
            }
        }

        return matrizInversa;
    }

    public double[] eigenValues() {
        int iteraciones = 150;
        Matrix matriz = new SquareMatrix(this.dimension);

        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                matriz.setValue(i, j, getValue(i, j));
            }
        }

        for (int i = 0; i < iteraciones; i++ ) {
            ContenedorQR qr = QR.desgloseQR(matriz);
            matriz = qr.R.multiplicar(qr.Q);
        }

        double[] eigenVals = new double[this.dimension];

        for (int k = 0; k < this.dimension; k++) {
            eigenVals[k] = matriz.getValue(k, k);
        }

        return eigenVals;
    }

    public Matrix eigenVectors() {
        int iteraciones = 200;
        int n = this.dimension;

        Matrix Ak = new SquareMatrix(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Ak.setValue(i, j, this.getValue(i, j));
            }
        }


        Matrix V = SquareMatrix.eyeMatrix(n);

        // --- BUCLE QR ---
        for (int i = 0; i < iteraciones; i++) {
            ContenedorQR qr = QR.desgloseQR(Ak);

            Ak = qr.R.multiplicar(qr.Q);

            V = V.multiplicar(qr.Q);
        }

        return V;
    }



} // final de la clase
