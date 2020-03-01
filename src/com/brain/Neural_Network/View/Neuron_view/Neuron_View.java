package com.brain.Neural_Network.View.Neuron_view;

import com.brain.Neural_Network.Neuron.Neuron;
import com.brain.Neural_Network.View.Neuron_view.primitives.Circle;
import com.brain.Neural_Network.View.Neuron_view.primitives.Line;

import java.awt.*;

public interface Neuron_View {
    double getError();
    void setError(double error);
    Circle getCircle();
    Line[] getLines();
    void setLines(double[] weights, Neuron[] layer);

    void draw_ellipse(Graphics g);
    void draw_line(Graphics g);
    void change_value(double signal);
}
