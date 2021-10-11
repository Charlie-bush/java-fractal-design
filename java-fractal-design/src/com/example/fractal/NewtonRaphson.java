package com.example.fractal;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewtonRaphson extends SwingWorker<BufferedImage, Void> {
    static final double DEFAULT_ZOOM       = 100.0;
    static final double DEFAULT_TOP_LEFT_X = -4.5;
    static final double DEFAULT_TOP_LEFT_Y =  4.0;

    private final double errorTerm = 1e-4;
    private final double maxIterations = 1e3;
    private Polynomial polynomial;

    private int width, height;
    private Map<Point, RootPoint> roots;
    private ArrayList<ComplexNumber> rootColours;

    public NewtonRaphson(int width, int height, Polynomial polynomial) {
        this.width = width;
        this.height = height;
        this.polynomial = polynomial;
    }
    double zoomFactor = DEFAULT_ZOOM;
    double topLeftX   = DEFAULT_TOP_LEFT_X;
    double topLeftY   = DEFAULT_TOP_LEFT_Y;

    private double getXPos(double x) {
        return (x / zoomFactor) + topLeftX;
    }
    private double getYPos(double y) {
        return (y / zoomFactor) - topLeftY;
    }

    private float clamp01(float value) {
        return Math.max(0, Math.min(1, value));
    }

    private Color getColorFromRoot(RootPoint rootPoint){
        for (int i = 0; i < rootColours.size(); i++) {
            if(rootColours.get(i).equals(rootPoint.getPoint(), errorTerm)){

                float hue, saturation, brightness;

                hue = clamp01(Math.abs((float)(0.5f - rootPoint.getPoint().argument() / (Math.PI * 2.0f))));

                saturation =  clamp01(Math.abs(0.59f / (float) rootPoint.getPoint().absoluteValue()));

                brightness = 0.95f * Math.max(1.0f - (float)rootPoint.getNumIter() * 0.025f, 0.05f);

                if (rootPoint.getPoint().absoluteValue() < 0.1) {
                    saturation = 0.0f;
                }

                return Color.getHSBColor(hue, saturation, brightness);
            }
        }
        return Color.black;
    }

    private void newtonMethod(int x, int y){
        ComplexNumber point = new ComplexNumber(getXPos(x), getYPos(y));

        RootApproximator rootApproximator = new RootApproximator(polynomial, point);

        RootPoint rootPoint = rootApproximator.getRootPoint();

        if(!containsRoot(rootPoint.getPoint())){
            rootColours.add(rootPoint.getPoint());
        }
        roots.put(new Point(x,y),rootPoint);
    }

    private boolean containsRoot(ComplexNumber root){
        for(ComplexNumber z: rootColours){
            if(z.equals(root, errorTerm)){
                return true;
            }
        }
        return false;
    }

    @Override
    protected BufferedImage doInBackground() {
        setProgress(0);
        BufferedImage fractalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        rootColours = new ArrayList<ComplexNumber>();
        roots = new HashMap<Point, RootPoint>();

        int totalSteps = 0;
        int calculationSteps =  width * height;

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if (isCancelled()) {
                    return fractalImage;
                }

                newtonMethod(x, y);

                Color c = getColorFromRoot(roots.get(new Point(x, y)));
                fractalImage.setRGB(x, y, c.getRGB());

                super.setProgress(Math.round(100.0f * (totalSteps++) / calculationSteps));
            }
        }
        return fractalImage;
    }

    private class RootApproximator {
        private Polynomial polynomial, dPolynomial;
        private ComplexNumber guess;

        public RootApproximator(Polynomial polynomial, ComplexNumber guess){
            this.polynomial = polynomial;
            this.dPolynomial = polynomial.derivative();
            this.guess = guess;
        }

        private double nextGuess(){
            ComplexNumber nextGuess = guess.subtract(polynomial.evaluate(guess).divide(dPolynomial.evaluate(guess)));
            double distance = nextGuess.euclideanDistance(guess);
            guess = nextGuess;
            return distance;
        }

        public RootPoint getRootPoint(){
            double diff = 10;
            int iter = 0;
            while(diff > errorTerm && iter < maxIterations){
                iter++;
                diff = nextGuess();
            }

            return new RootPoint(this.guess, iter);
        }
    }
    private class RootPoint {
        private ComplexNumber point;
        private int numIter;

        public RootPoint(ComplexNumber point, int numIter){
            this.point = point;
            this.numIter = numIter;
        }
        public ComplexNumber getPoint() {
            return this.point;
        }

        public int getNumIter() {
            return this.numIter;
        }

    }


}
