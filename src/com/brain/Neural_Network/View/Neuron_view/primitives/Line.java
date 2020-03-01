package com.brain.Neural_Network.View.Neuron_view.primitives;

import com.brain.Neural_Network.View.Geometry;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Line extends Line2D{
    private Point p1;
    private Point p2;


    private Color color;
    private float thickness;

    public float getThickness() {
        return thickness;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Line(double x1, double y1, double x2, double y2, double weight) {
        this.p1 = new Point((int)x1 + Geometry.neuron_radius/2,(int)y1 + Geometry.neuron_radius/2);
        this.p2 = new Point((int)x2 + Geometry.neuron_radius/2,(int)y2 + Geometry.neuron_radius/2);
        setColorAndThickness(weight);
    }

    public void setColorAndThickness(double weight){
        if (weight > 0){
            this.color = Color.BLUE;
            this.thickness = (float) (6*Math.atan(weight));
        } else {
            this.color = Color.RED;
            this.thickness = (float) (-6*Math.atan(weight));
        }
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







}
