package linearalgebra;

public class SquareMatrix<T extends Number> extends Matrix<T> {

    // atributos
    private int dimension;

    // Constructores 
    public SquareMatrix(int dimension) {
        // Llamamos al constructor padre con filas=dimension y columnas=dimension
        super(dimension, dimension); 
        this.dimension = dimension;
    }

    // Constructor 2: Recibe datos ya existentes
    public SquareMatrix(T[][] values) {
        super(values);
        // Validación extra: Debe ser cuadrada
        if (this.getNrows() != this.getNcols()) {
            throw new IllegalArgumentException("Los datos no forman una matriz cuadrada.");
        }
    }

    // Metodos que solo tiene la matriz cuadrada

    public static SquareMatrix<Double> eyeMatrix(int dimension) {

        SquareMatrix<Double> matriz = new SquareMatrix<>(dimension);

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
            suma += this.values[i][i].doubleValue();
        }
        return suma;
    }

    public boolean esSimetrica() {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < i; j++) { // Solo recorremos el triángulo inferior
                double valA = this.values[i][j].doubleValue();
                double valB = this.values[j][i].doubleValue();
                
                if (valA != valB) return false;
            }
        }
        return true;
    }

    public double getDeterminante() {
        return determinanteRecursivo(this.values);
    }

    private double determinanteRecursivo(Number[][] matrizActual) {
        int n = matrizActual.length;
        // Caso Matriz 1x1
        if (n == 1) {
            return matrizActual[0][0].doubleValue();
        }

        // Caso Matriz 2x2 (
        if (n == 2) {
            double a = matrizActual[0][0].doubleValue();
            double b = matrizActual[0][1].doubleValue();
            double c = matrizActual[1][0].doubleValue();
            double d = matrizActual[1][1].doubleValue();
            return (a * d) - (b * c);
        }

        // Caso Recursivo: Expansión de Laplace
        double determinante = 0;
        for (int c = 0; c < n; c++) {
            // Alternancia de signos (+ - + -)
            double signo = (c % 2 == 0) ? 1.0 : -1.0;
            double valorActual = matrizActual[0][c].doubleValue();
            
            // Si el valor es 0, saltamos el cálculo para optimizar
            if (valorActual == 0) continue;

            Number[][] subMatriz = crearSubMatriz(matrizActual, 0, c);
            determinante += signo * valorActual * determinanteRecursivo(subMatriz);
        }

        return determinante;
    }

    private Number[][] crearSubMatriz(Number[][] original, int filaAExcluir, int colAExcluir) {
        int n = original.length;
        Number[][] nueva = new Number[n-1][n-1];
        
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

    public SquareMatrix<Double> getInversa() {

        double det = this.getDeterminante();

        // Si el determinante es 0, matemáticamente no existe inversa
        if (Math.abs(det) < 1e-10) { 
            throw new ArithmeticException("La matriz es singular (Determinante = 0), no tiene inversa.");
        }

        int n = this.getNrows();
        SquareMatrix<Double> matrizInversa = new SquareMatrix<>(n);

        // Caso  Matriz de 1x1
        if (n == 1) {
            matrizInversa.setValue(0, 0, 1.0 / det);
            return matrizInversa;
        }

        // Construir la Matriz Adjunta (Cofactores Transpuestos) / Determinante
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // btener la submatriz eliminando fila i y columna j
                Number[][] subMatriz = crearSubMatriz(this.values, i, j);

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


} // final de la clase
