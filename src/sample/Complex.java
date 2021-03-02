package sample;

public class Complex {
    private double real;
    private double imaginary;

    public Complex() {
    }

    public Complex(double real) {
        this.real = real;
    }

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        return  real + " " + imaginary+ "i";
    }
}
