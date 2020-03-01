package com.brain.Neural_Network.View.Neuron_view;

import com.brain.Neural_Network.Neuron.Hidden_Neuron;
import com.brain.Neural_Network.Neuron.Inlet_Knot;
import com.brain.Neural_Network.Neuron.Neuron;
import com.brain.Neural_Network.View.Neuron_view.primitives.Circle;
import com.brain.Neural_Network.View.Neuron_view.primitives.Line;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

public class Hidden_Neuron_View implements Neuron_View {
    private Circle circle;
    private Line[] lines;
    private double value;
    private double error;

    public Hidden_Neuron_View(double x, double y, double radius, double value) {
        this.circle = new Circle(x,y,radius);
        this.value = Math.round(value*100.0)/100.0;
    }

    @Override
    public void setLines(double[] weights, Neuron[] layer) {
        this.lines = new Line[
                (int) Arrays.stream(layer).
                        filter((x) -> x instanceof Inlet_Knot || x instanceof Hidden_Neuron).count()
                ];
        for (int i = 0; i < this.lines.length; i++) {
            Line line = new Line(circle.getX(),circle.getY(),
                    layer[i].get_Neuron_View().getCircle().getX(),
                    layer[i].get_Neuron_View().getCircle().getY(),
                    weights[i]);
            lines[i] = line;
        }
    }


    @Override
    public double getError() {
        return error;
    }

    @Override
    public void setError(double error) {
        this.error = Math.round(error*10000.0)/10000.0;
    }

    @Override
    public Circle getCircle() {
        return circle;
    }

    @Override
    public Line[] getLines() {
        return lines;
    }


    @Override
    public void draw_ellipse(Graphics g){
        Graphics2D back = (Graphics2D)g;
        back.setStroke(new BasicStroke(5f));
        back.setPaint(new Color(168, 22, 212));
        back.draw(new Ellipse2D.Double(this.circle.getX() + (this.circle.getWidth()-76)/2,
                this.circle.getY() + (this.circle.getWidth()-76)/2,
                76,
                76));

        Graphics2D g2D = (Graphics2D)g;

        g2D.setPaint(Color.BLACK);
        g2D.setStroke(new BasicStroke(2f));
        g2D.draw(this.circle);
        g2D.fill(this.circle);

        Graphics2D value = (Graphics2D)g;
        value.setPaint(Color.WHITE);
        value.setFont(new Font("Arial",Font.BOLD,15));
        value.drawString("v = "+String.valueOf(this.value),
                (int)this.circle.getX()+(int)(this.circle.getWidth()/8),
                (int)this.circle.getY()+(int)(this.circle.getWidth())/3);

        Graphics2D error = (Graphics2D)g;
        error.setPaint(Color.RED);
        error.setFont(new Font("Arial",Font.BOLD,15));
        error.drawString(String.valueOf(this.error),
                (int)this.circle.getX()+(int)(this.circle.getWidth()/8),
                (int)this.circle.getY()+50);
    }

    @Override
    public void draw_line(Graphics g){
        for (Line line : this.lines) {
            Graphics2D g2D = (Graphics2D)g;
            g2D.setStroke(new BasicStroke(line.getThickness()));
            g2D.setPaint(line.getColor());
            g2D.draw(line);
        }

    }

    @Override
    public void change_value(double value){
        this.value = Math.round(value*100.0)/100.0;
    }


}