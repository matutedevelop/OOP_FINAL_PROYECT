package complex;

import linearalgebra.Matrix;

public class ComplexNumber {
    private  double real;
    private double imaginary;

    // Constructor
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Getters
    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    // Metodo de las clase Complex Numver

    public ComplexNumber sumar(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }


    public ComplexNumber restar(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }


    public ComplexNumber multiplicar(ComplexNumber other) {
        double newReal = (this.real * other.real) - (this.imaginary * other.imaginary);
        double newImaginary = (this.real * other.imaginary) + (this.imaginary * other.real);
        return new ComplexNumber(newReal, newImaginary);
    }

    // División
    public ComplexNumber dividir(ComplexNumber other) {
        double denominator = (other.real * other.real) + (other.imaginary * other.imaginary);

        if (denominator == 0) {
            throw new ArithmeticException("No se puede dividir por cero 0");
        }
        
        double newReal = ((this.real * other.real) + (this.imaginary * other.imaginary)) / denominator;
        double newImaginary = ((this.imaginary * other.real) - (this.real * other.imaginary)) / denominator;
        
        return new ComplexNumber(newReal, newImaginary);
    }

    public double modulo() {
        return Math.sqrt((real * real) + (imaginary * imaginary));
    }

    // Conjugado: z' = a - bi
    public ComplexNumber conjugado() {
        return new ComplexNumber(this.real, -this.imaginary);
    }

    // Metodos Canonicos
    @Override
    public String toString() {
        if (imaginary == 0) return "" + real;
        if (real == 0) return imaginary + "i";
        // Formato para manejar signos correctamente (ej: 3.0 - 2.0i en vez de 3.0 + -2.0i)
        if (imaginary < 0) {
            return real + " - " + (-imaginary) + "i";
        }
        return real + " + " + imaginary + "i";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Todo es igual a si mismo
        if (obj == null) return false; // NADA es igual a null

        if (! (obj instanceof Matrix)) return false; // para ver si son el mismo tipo de instancia

        ComplexNumber num = (ComplexNumber) obj;

        return num.getImaginary() == this.imaginary && num.getReal() == this.real;
    }

    @Override
    public Object clone() {
        return new ComplexNumber(this.real, this.imaginary);
    }

}
