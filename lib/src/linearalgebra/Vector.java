package linearalgebra;

import enume.Angulo;

public class Vector<T extends Number> {
    
    // atributos
    private int dimension;
    private Number[] values;

    // constructores
    public Vector(int dimension) {
        // Validación si la dimension son menores o iguales a 0
        if (dimension <= 0) {
            throw new IllegalArgumentException("La dimension debe ser mayor a 0. Recibido: " + dimension);
        }

        this.dimension = dimension;
        this.values = new Number[dimension];

        // Copia profunda para evitar referencias externas
        for (int i = 0; i < dimension; i++) {
            this.values[i] = 0.0;
        }
    }

    public Vector(T[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("El array de datos no puede ser nulo ni estar vacío.");
        }

        this.dimension = values.length;
        this.values = new Number[values.length];

        // Copia profunda para evitar referencias externas
        for (int i = 0; i < values.length; i++) {
            setValue(i, values[i]);
        }
    }


    public Vector(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("El array de datos no puede ser nulo ni estar vacío.");
        }

        this.dimension = values.length;
        this.values = new Number[values.length];

        // Copia profunda para evitar referencias externas
        for (int i = 0; i < values.length; i++) {
            this.values[i]  = values[i];
        }
    }


    // setters y getters

    public int getDimension() {
        return dimension;
    }

    public double getValue(int indice) {
        if (indice < 0 || indice >= this.dimension) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        return values[indice].doubleValue();
    }

    public void setValue(int indice, T value) {
        if (indice < 0 || indice >= this.dimension) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        
        this.values[indice] = value;
    }

    // metodos de la clase Vectores

    public Vector<Double> sumar(Vector<? extends Number> vector) {
        if (this.dimension != vector.getDimension()) {
            throw new IllegalArgumentException("Las dimensiones deben ser iguales para sumar.");
        }

        Vector<Double> resultado = new Vector<>(this.dimension);

        for (int i = 0; i < resultado.getDimension(); i++) {
                
            double valA = this.values[i].doubleValue();
            double valB = vector.values[i].doubleValue();

            resultado.setValue(i,valA + valB);
        }

        return resultado;
    } // fin de la suma 

    public Vector<Double> restar(Vector<? extends Number> vector) {
        if (this.dimension != vector.getDimension()) {
            throw new IllegalArgumentException("Las dimensiones deben ser iguales para sumar.");
        }

        Vector<Double> resultado = new Vector<>(this.dimension);

        for (int i = 0; i < resultado.getDimension(); i++) {
                
            double valA = this.values[i].doubleValue();
            double valB = vector.values[i].doubleValue();

            resultado.setValue(i,valA - valB);
        }

        return resultado;
    } // fin de la resta
    

    public Vector<Double> multiplicarEscalar(double escalar) {

        Vector<Double> resultado = new Vector<>(this.dimension);

        for (int i = 0; i < resultado.getDimension(); i++) {

            resultado.setValue(i,escalar * this.getValue(i));
        }

        return resultado;
    } // fin de la multiplicacion

    public Vector<Double> sumarEscalar(double escalar) {

        Vector<Double> resultado = new Vector<>(this.dimension);

        for (int i = 0; i < resultado.getDimension(); i++) {

            resultado.setValue(i,escalar + this.getValue(i));
        }

        return resultado;
    } // fin de la suma

    public double dot(Vector<? extends Number> vector) {
        if (this.dimension != vector.getDimension()) {
            throw new IllegalArgumentException("Las dimensiones deben ser iguales para sumar.");
        }

        double total = 0.0;

        for (int i = 0; i < this.dimension; i++) {
            total += this.getValue(i) * vector.getValue(i);
        }

        return total;
    }

    public double getNorm() {
        double suma = 0.0;

        for (int i = 0; i < this.dimension; i++) {
            double valor = this.getValue(i);
            suma += valor * valor;
        }

        return Math.sqrt(suma);
    }

    public Vector<Double> normalize() {

        double mag = this.getNorm();
        if (mag == 0) {
            throw new ArithmeticException("No se puede normalizar un vector nulo");
        }

        Vector<Double> resultado = new Vector<>(this.dimension);

        resultado = resultado.multiplicarEscalar(1 / mag);

        return resultado;
    }

    public double angleVector(Vector<? extends Number> otro, Angulo angulo) {
        double dot = this.dot(otro);
        double magA = this.getNorm();
        double magB = otro.getNorm();

        if (magA == 0 || magB == 0) {
            throw new ArithmeticException("No se puede calcular el ángulo con un vector cero (magnitud 0).");
        }

        double coseno = dot / (magA * magB);

        // Correcion
        if (coseno > 1.0) coseno = 1.0;
        if (coseno < -1.0) coseno = -1.0;

        if (angulo.value() == 1){
            return Math.acos(coseno);
        }
        else {
            return Math.acos(coseno) * 180 / Math.PI;
        }
    }


    // Metodos canonicos

     @Override
    public boolean equals(Object obj){
        if (this == obj) return true;  // Todo es igual a si mismo
        if (obj == null) return false; // NADA es igual a null

        if (! (obj instanceof Matrix)) return false; // para ver si son el mismo tipo de instancia

        Vector<?> vector = (Vector<?>) obj;

        if (this.dimension != vector.getDimension()) {
            return false;
        }

        // Si llegamos hasta aquí es porque si tenemos el mismo tipo de Matriz
        for (int i = 0; i < this.dimension; i++) {
            if (this.getValue(i) != vector.getValue(i)) {
                return false;
            }
        }

        return true;
        
    }

    @Override
    public String toString(){
        String vector = "";
        vector += "[ ";
        for (int i = 0; i < this.dimension; i++) {
            
            vector += String.format("%.4f", getValue(i)) + ", "; 
        }

        vector += "]\n";
        return vector;
    }

    @Override
    public Object clone(){
        Vector<Double> vector = new Vector<>(this.dimension);

        for (int i = 0; i < this.dimension; i++) {
            vector.setValue(i, getValue(i));

        }

        return vector;
    }


} // final de la clase