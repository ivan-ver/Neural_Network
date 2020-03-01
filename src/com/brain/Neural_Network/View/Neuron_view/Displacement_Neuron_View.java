package com.brain.Neural_Network.View.Neuron_view;

import com.brain.Neural_Network.Neuron.Neuron;
import com.brain.Neural_Network.View.Neuron_view.primitives.Circle;
import com.brain.Neural_Network.View.Neuron_view.primitives.Line;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Displacement_Neuron_View implements Neuron_View {
    private Circle circle;
    private Line[] lines;

    public Displacement_Neuron_View(double x, double y, double radius) {
        this.circle = new Circle(x,y,radius-30);

    }

    @Override
    public void setLines(double[] weights, Neuron[] layer) {
        this.lines = new Line[layer.length];
        for (int i = 0; i < this.lines.length; i++) {
            Line line = new Line(circle.getX() - circle.getWidth()/2,circle.getY() - circle.getWidth()/2,
                    layer[i].get_Neuron_View().getCircle().getX(),
                    layer[i].get_Neuron_View().getCircle().getY(),
                    weights[i]);
            lines[i] = line;
        }
    }


    @Override
    public void draw_ellipse(Graphics g) {
        Graphics2D back = (Graphics2D)g;
        back.setStroke(new BasicStroke(5f));
        back.setPaint(new Color(212, 106, 1));
        back.draw(new Ellipse2D.Double(this.circle.getX() + (this.circle.getWidth()-46)/2,
                this.circle.getY() + (this.circle.getWidth()-46)/2,
                46,
                46));

        Graphics2D g2D = (Graphics2D)g;
        g2D.setPaint(Color.pink);
        g2D.setStroke(new BasicStroke(2f));
        g2D.draw(this.circle);
        g2D.fill(this.circle);
    }

    @Override
    public void draw_line(Graphics g) {
        for (Line line : this.lines) {
            Graphics2D g2D = (Graphics2D)g;
            g2D.setPaint(line.getColor());
            g2D.setStroke(new BasicStroke(line.getThickness()));
            g2D.draw(line);
        }
    }

    @Override
    public Line[] getLines() {
        return this.lines;
    }

    @Override
    public double getError() {
        return 0;
    }

    @Override
    public void setError(double error) {

    }

    @Override
    public Circle getCircle() {
        return this.circle;
    }

    @Override
    public void change_value(double value) {

    }

}
