package com.brain.view.primitives;

import java.awt.geom.Point2D;

public class SimplePoint extends Point2D {
    private double x;
    private double y;

    public SimplePoint(double x, double y) {
        this.x = x;
        this.y = y;
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
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
