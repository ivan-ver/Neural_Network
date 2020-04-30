package com.animals;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Food extends JComponent {
    private Ellipse2D ellipse2D;
    private Color color = Color.red;
    private double X;
    private double Y;

    public Food(double x, double y) {
        this.X = x;
        this.Y = y;

        setFood();
    }
    private void setFood(){
        this.ellipse2D = new Ellipse2D.Double(
                this.X - Main_geometry.foodDiameter/2,
                this.Y - Main_geometry.foodDiameter/2,
                Main_geometry.foodDiameter,
                Main_geometry.foodDiameter);
    }

    public void draw(Graphics g) {
        Graphics2D graph = (Graphics2D)g;
        graph.setPaint(color);

        graph.draw(this.ellipse2D);
        graph.fill(this.ellipse2D);
    }

    public Ellipse2D getEllipse2D() {
        return this.ellipse2D;
    }
}
