package com.brain.view.primitives;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SimpleLine extends Line2D{
    private SimplePoint p1;
    private SimplePoint p2;

    private double x0;
    private double y0;

    private Color color;

    private float thickness;

    public float getThickness() {
        return thickness;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public SimpleLine(double x1, double y1, double x2, double y2) {
        this.p1 = new SimplePoint((int)x1,(int)y1);
        this.p2 = new SimplePoint((int)x2,(int)y2);

        x0 = p1.getX();
        y0 = p1.getY();
    }

    @Override
    public double getX1() {
        return p1.getX();
    }

    @Override
    public double getY1() {
        return p1.getY();
    }

    @Override
    public Point2D getP1() {
        return this.p1;
    }

    @Override
    public double getX2() {
        return p2.getX();
    }

    @Override
    public double getY2() {
        return p2.getY();
    }

    @Override
    public Point2D getP2() {
        return this.p2;
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {
        p1.setLocation(x1,y1);
        p2.setLocation(x2,y2);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    public void changeColorAndThickness(){

        float number = (float) (Math.random()*14);

        this.color = number < 7 ? Color.BLUE : Color.RED;

        this.thickness = number;
    }






}
