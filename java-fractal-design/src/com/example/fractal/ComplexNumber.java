package com.example.fractal;

public class ComplexNumber {
    private double realPart;
    private double imaginaryPart;

    public ComplexNumber multiply(ComplexNumber z){
        ComplexNumber result = new ComplexNumber();
        //(a+bi) * (c +di) = ac -bd + (cb + ad)i
        result.setRealPart(this.realPart* z.getRealPart() - this.imaginaryPart *z.getImaginaryPart());
        result.setImaginaryPart(this.realPart* z.getImaginaryPart() + this.imaginaryPart *z.getRealPart());
        return result;
    }

    public ComplexNumber subtract(ComplexNumber z){
        ComplexNumber result = new ComplexNumber();
        //(a+bi) - (c +di) = a-c + (b-d)i
        result.setRealPart(this.realPart - z.getRealPart());
        result.setImaginaryPart(this.imaginaryPart - z.getImaginaryPart());
        return result;
    }
    public ComplexNumber add(ComplexNumber z){
        ComplexNumber result = new ComplexNumber();
        //(a+bi) + (c +di) = a+c + (b+d)i
        result.setRealPart(this.realPart + z.getRealPart());
        result.setImaginaryPart(this.imaginaryPart + z.getImaginaryPart());
        return result;
    }
    public ComplexNumber conjugate(){
        ComplexNumber result = new ComplexNumber(this.getRealPart(), - this.imaginaryPart);
        return result;
    }

    public ComplexNumber divide(ComplexNumber z){
        ComplexNumber result = new ComplexNumber();
        ComplexNumber numerator = this.multiply(z.conjugate());
        ComplexNumber denominator = z.multiply(z.conjugate());
        double denominatorDouble = denominator.getRealPart();
        //Divide by zero error here catch with null
        if(denominatorDouble==0){
            return null;
        }

        result.setRealPart(numerator.getRealPart()/denominatorDouble);
        result.setImaginaryPart(numerator.getImaginaryPart()/denominatorDouble);

        return result;
    }

    public ComplexNumber() {
        this.realPart = 0;
        this.imaginaryPart = 0;
    }

    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }
}
