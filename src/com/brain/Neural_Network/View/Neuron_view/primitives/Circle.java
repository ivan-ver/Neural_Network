package com.brain.Neural_Network.View.Neuron_view.primitives;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Circle extends Ellipse2D {

    private double x;
    private double y;
    private double width;
    private double height;


    public Circle(double x, double y, double radius) {
        this.x = x - radius/2;
        this.y = y + radius/2;
        this.width = radius;
        this.height = radius;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {

    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }
}
