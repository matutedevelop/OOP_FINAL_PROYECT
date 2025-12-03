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


} // final de la clase
