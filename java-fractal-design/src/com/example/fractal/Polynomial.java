package com.example.fractal;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private List<Double> coefficients = new ArrayList<>();
    private List<Double> derivatives;

    private final double errorTerm = 1e-6;

    public Polynomial(List<Double> coefficients){
        this.coefficients = coefficients;
    }

    public List<Double> findRoots(List<Double> coefficients) {
        List<Double> roots = new ArrayList<>();
        return roots;
    }

    private void derivativeCalculator(){
        List<Double> derivatives = new ArrayList<>();
        int i=0;
        for(Double coefficient: coefficients){
            if(i!=0){
                derivatives.add(coefficient*i);
            }
            i++;
        }
        this.derivatives = derivatives;
    }

    private double evaluationOfPolynomial(double x){
        double evaluation =0;
        for (int i = 0; i < coefficients.size(); i++){
            evaluation += coefficients.get(i) * Math.pow(x,i);
        }
        return evaluation;
    }
    private double evaluationOfDerivateOfPolynomial(double x){
        double evaluation =0;
        for (int i = 0; i < derivatives.size(); i++){
            evaluation += derivatives.get(i) * Math.pow(x,i);
        }
        return evaluation;
    }
    public double getValuation(double x){
        return evaluationOfPolynomial(x);
    }
    public double getDerivativeEvaluation(double x){
        return evaluationOfDerivateOfPolynomial(x);
    }

    public List<Double> getCoefficients() {
        return coefficients;
    }

    public List<Double> getDerivatives() {
        if(derivatives==null){
            this.derivativeCalculator();
        }
        return derivatives;
    }
}
