package linearalgebra;

public class Matrix {

    private int ncols;
    private int nrows;

    protected double[][] values;

    // constructores
    public Matrix(int nrows, int ncols) {
        // Validación: Si filas O columnas son menores o iguales a 0
        if (nrows <= 0 || ncols <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores a 0. Recibido: " + nrows + " x " + ncols);
        }

        this.nrows = nrows;
        this.ncols = ncols;
        this.values = new double[nrows][ncols];

        // Copia profunda para evitar referencias externas
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {

                this.values[i][j] = 0.0;
            }
        }

    }

    public Matrix(double[][] values) {

        if (values == null || values.length == 0 || values[0].length == 0) {
            throw new IllegalArgumentException("El array de datos no puede ser nulo ni estar vacío.");
        }

        this.nrows = values.length;
        this.ncols = values[0].length;
        this.values = new double[nrows][ncols];
        
        // Copia profunda para evitar referencias externas
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {

                setValue(i, j, values[i][j]);
            }
        }

    }

    // setter y getters

    public double getValue(int row, int col) {
        validarIndices(row, col);
        return this.values[row][col];
    }

    public void setValue(int row, int col, double valor) {
        validarIndices(row, col);
        this.values[row][col] = valor;
    }

    public int getNcols() {
        return ncols;
    }

    public int getNrows() {
        return nrows;
    }

    public int[] getShape() {
        int[] shape = {getNrows(), getNcols()};
        return shape;
    }

    public double[] getColumn(int col) {
        if (col < 0 || col >= this.ncols) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        double[] columna = new double[this.nrows];
        
        for (int i = 0; i < this.nrows; i++) {
            columna[i] = this.values[i][col];
        }

        return columna;
    }
    
    public double[] getRow(int row) {
        if (row < 0 || row >= this.nrows) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        double[] fila = new double[this.ncols];
        
        for (int i = 0; i < this.ncols; i++) {
            fila[i] = this.values[row][i];
        }

        return fila;
    }

    // OPERACIOENS BASICAS DE LAS MATRICES
    public Matrix sumar(Matrix matriz) {
        if (this.nrows != matriz.nrows || this.ncols != matriz.ncols) {
            throw new IllegalArgumentException("Las dimensiones deben ser iguales para sumar.");
        }

        Matrix resultado = new Matrix(this.nrows, this.ncols);

        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                
                double valA = this.values[i][j];
                double valB = matriz.values[i][j];

                resultado.setValue(i, j, valA + valB);
            }
        }

        return resultado;
    } // fin de la suma 


    public Matrix restar(Matrix matriz) {
        if (this.nrows != matriz.nrows || this.ncols != matriz.ncols) {
            throw new IllegalArgumentException("Las dimensiones deben ser iguales para sumar.");
        }

        Matrix resultado = new Matrix(this.nrows, this.ncols);

        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                
                double valA = this.values[i][j];
                double valB = matriz.values[i][j];

                resultado.setValue(i, j, valA - valB);
            }
        }

        return resultado;
    } // fin de la resta


    public Matrix multiplicar(Matrix matriz) {
        if (this.ncols != matriz.getNrows()) {
            throw new IllegalArgumentException(
                "Columnas de A (" + this.ncols + ") no coinciden con Filas de B (" + matriz.getNrows() + ")."
            );
        }

        Matrix resultado = new Matrix(this.nrows, matriz.getNcols());

        // Algoritmo de multiplicación de matrices
        for (int i = 0; i < this.nrows; i++) {           
            for (int j = 0; j < matriz.getNcols(); j++) {   
                for (int k = 0; k < this.nrows; k++) { 
                    resultado.values[i][j] = resultado.values[i][j] + 
                                            this.values[i][k] * matriz.values[k][j];
                }
            }
        }

        return resultado;
    } // final de multiplicar


    public Matrix multiplicarEscalar(double escalar) {

        Matrix resultado = new Matrix(this.nrows, this.ncols);

        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
   
                resultado.setValue(i, j, escalar * this.values[i][j]);
            }
        }

        return resultado;
    }



    public Matrix transpuesta() {

        Matrix resultado = new Matrix(this.ncols, this.nrows);

        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                resultado.values[j][i] = this.values[i][j];
            }
        }
        return resultado;
    }



    private void validarIndices(int row, int col) {
        if (row < 0 || row >= this.nrows || col < 0 || col >= this.ncols) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
    }





    // Metodos Canonicos

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;  // Todo es igual a si mismo
        if (obj == null) return false; // NADA es igual a null

        if (! (obj instanceof Matrix)) return false; // para ver si son el mismo tipo de instancia

        Matrix matriz = (Matrix) obj;

        if (this.nrows != matriz.getNrows() || this.ncols != matriz.getNcols()) {
            return false;
        }

        // Si llegamos hasta aquí es porque si tenemos el mismo tipo de Matriz
        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                if (this.values[i][j] != matriz.values[i][j]) {
                    return false;
                }
            }
        }

        return true;
        
    }

    @Override
    public String toString(){
        String matriz = "";

        for (int i = 0; i < nrows; i++) {
            matriz += "[ ";
            for (int j = 0; j < ncols; j++) {
                matriz += String.format("%.4f", values[i][j]) + "\t";
            }

            matriz += "]\n";
        }

        return matriz;
    }

    @Override
    public Object clone(){
        Matrix matriz = new Matrix(this.nrows, this.ncols);

        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                matriz.values[i][j] = getValue(i, j);
            }
        }


        return matriz;
    }


}  // final de la clase
