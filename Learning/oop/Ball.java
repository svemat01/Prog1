package oop;

public class Ball {
// Instance Variables
    private double radius = 5.0;
    private double massRatio = 1.0;
    private String color;

    private double velocity;

// Constructor
    public Ball(String color) {
        this.color = color;
    }

    public Ball(String color, double radius) {
        this.color = color;
        this.radius = radius;
    }

    public Ball(String color, double radius, double massRatio) {
        this.color = color;
        this.radius = radius;
        this.massRatio = massRatio;
    }

// Methods
    //    Volume
    public double getVolume() {
        return (4/3) * Math.pow(radius, 3) * Math.PI;
    }

    //    MassRatio
    public void setMassRatio(double massRatio) {
        this.massRatio = massRatio;
    }

    public double getMassRatio() {
        return massRatio;
    }
}
