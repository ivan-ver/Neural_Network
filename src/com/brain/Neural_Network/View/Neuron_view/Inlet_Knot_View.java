package com.brain.Neural_Network.View.Neuron_view;

import com.brain.Neural_Network.Neuron.Neuron;
import com.brain.Neural_Network.View.Neuron_view.primitives.Circle;
import com.brain.Neural_Network.View.Neuron_view.primitives.Line;

import java.awt.*;

public class Inlet_Knot_View implements Neuron_View {

    private Circle circle;
    private double value;

    public Inlet_Knot_View(double x, double y, double radius, double value) {
        this.value = Math.round(value*100.0)/100.0;
        this.circle = new Circle(x,y,radius);
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
    public Line[] getLines() {
        return new Line[0];
    }

    @Override
    public void setLines(double[] weights, Neuron[] layer) {

    }



    @Override
    public void draw_ellipse(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;

        g2D.setPaint(Color.GRAY);
        g2D.setStroke(new BasicStroke(2f));
        g2D.draw(this.circle);
        g2D.fill(this.circle);

        Graphics2D value = (Graphics2D)g;
        value.setPaint(Color.WHITE);
        value.setFont(new Font("Arial",Font.BOLD,18));
        value.drawString(String.valueOf(this.value),
                (int)this.circle.getX() + 18,
                (int)this.circle.getY() + 40);

    }

    @Override
    public void draw_line(Graphics g) {

    }

    @Override
    public void change_value(double value) {
        this.value = Math.round(value*100.0)/100.0;
    }

}
