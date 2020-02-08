package com.brain.view;

import com.brain.view.primitives.SimpleEllipse;
import com.brain.view.primitives.SimpleLine;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

class DrawNeuron {
    private DrawNeuron[] previous_layer;
    private SimpleEllipse ellipse;
    private SimpleLine[] lines;
    private double radius = 70.0;

    public SimpleEllipse getEllipse() {
        return ellipse;
    }

    public SimpleLine[] getLines() {
        return lines;
    }

    public void setLines() {
        lines = new SimpleLine[previous_layer.length];
        for (int i = 0; i < previous_layer.length; i++) {
           SimpleLine line = new SimpleLine(ellipse.getX() + this.radius/2,
                    ellipse.getY() + this.radius/2,
                    previous_layer[i].getEllipse().getX() + this.radius/2,
                    previous_layer[i].getEllipse().getY() + this.radius/2);
           line.setColor(Color.BLACK);
           lines[i] = line;
        }
    }

    public DrawNeuron(double x, double y) {
        this.ellipse = new SimpleEllipse(x,y,radius,radius);
    }

    public void setPrevious_layer(DrawNeuron[] previous_layer) {
        this.previous_layer = previous_layer;
    }

    public void draw_ellipse(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setPaint(Color.BLACK);
        g2D.setStroke(new BasicStroke(2f));

        g2D.draw(this.ellipse);
        g2D.fill(this.ellipse);
       }

    public void draw_line(Graphics g){

        for (SimpleLine line : this.lines) {
            Graphics2D g2D = (Graphics2D)g;
            g2D.setPaint(line.getColor());
            g2D.setStroke(new BasicStroke(line.getThickness()));
            g2D.draw(line);
        }

    }
}