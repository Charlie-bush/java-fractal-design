package com.example.fractal;

public class NewtonRaphson {
    private final double errorTerm = 1e-6;
    private final double maxIterations = 1e6;
    private Polynomial polynomial;
    private Resolution resolution;

    public NewtonRaphson(Polynomial polynomial, Resolution resolution) {
        this.polynomial = polynomial;
        this.resolution = resolution;
    }

    private double findRootOfPoint(double x){
        int iterations =0;
        double oldValue = x;
        double newValue = oldValue - polynomial.getValuation(oldValue)/polynomial.getDerivativeEvaluation(oldValue);
        while(Math.abs(newValue-oldValue)<errorTerm || iterations < maxIterations){
            iterations++;
            oldValue = newValue;
            newValue = oldValue - polynomial.getValuation(oldValue)/polynomial.getDerivativeEvaluation(oldValue);
        }
        return newValue;
    }


}
