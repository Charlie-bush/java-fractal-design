package com.example.fractal;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    public double[] coefficients;

    public Polynomial(double[] coefficients){
        this.coefficients = coefficients;
    }

    public Polynomial derivative(){
        double[] derivatives = new double[coefficients.length - 1];
        for(int i = 0; i < derivatives.length; i++){
            derivatives[i] = coefficients[i + 1] * (i + 1);
        }
        return new Polynomial(derivatives);

    }

    public ComplexNumber evaluate(ComplexNumber z) {
        ComplexNumber result = new ComplexNumber();
        for(int i = 0; i < coefficients.length; i++) {
            if(i == 0) {
                result = result.add(new ComplexNumber(coefficients[0],0));
                continue;
            }
            if(coefficients[i] == 0) {
                continue;
            }
            result = result.add(z.complexPower(i).multiply(new ComplexNumber(coefficients[i],0)));
        }
        return result;
    }

}
