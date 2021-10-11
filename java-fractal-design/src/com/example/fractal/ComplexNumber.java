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
        double k = Math.pow(z.imaginaryPart, 2) + Math.pow(z.realPart, 2);
        return new ComplexNumber( (this.realPart * z.realPart + this.imaginaryPart * z.imaginaryPart) / k,
                (this.imaginaryPart * z.realPart - this.realPart * z.imaginaryPart) / k);
    }

    public ComplexNumber() {
        this.realPart = 0;
        this.imaginaryPart = 0;
    }

    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }
    public double absoluteValue(){
        return Math.sqrt(Math.pow(this.realPart,2)+Math.pow(this.imaginaryPart,2));
    }
    public double argument(){
        return Math.atan2(this.imaginaryPart,this.realPart);
    }

    public ComplexNumber complexPower(double power){
        double r = Math.pow(this.absoluteValue(),power);
        double theta = power * this.argument();
        return new ComplexNumber(Math.cos(theta)*r,Math.sin(theta)*r);
    }
    public boolean equals(ComplexNumber z, double tolerance){
        return (euclideanDistance(z) <= tolerance);
    }
    public double euclideanDistance(ComplexNumber z){
        return Math.sqrt(Math.pow(this.realPart-z.getRealPart(),2)+Math.pow(this.imaginaryPart-z.getImaginaryPart(),2));
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
