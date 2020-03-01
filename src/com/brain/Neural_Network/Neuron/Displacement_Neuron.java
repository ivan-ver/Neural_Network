package com.brain.Neural_Network.Neuron;

import com.brain.Neural_Network.View.Neuron_view.Displacement_Neuron_View;
import com.brain.Neural_Network.View.Neuron_view.Neuron_View;

import java.io.Serializable;

public class Displacement_Neuron implements Neuron, Serializable {
    private double SIGNAL;
    private double[] weights;
    private Neuron[] layer;
    private Displacement_Neuron_View displacement_neuron_view;

    public Displacement_Neuron() {
        this.SIGNAL = 1.0;

    }

    @Override
    public Neuron[] getLayer() {
        return this.layer;
    }

    @Override
    public Neuron_View get_Neuron_View() {
        return this.displacement_neuron_view;
    }

    @Override
    public void set_Layer(Neuron[] layer) {
        this.layer = layer;
    }

    @Override
    public void set_View(int x, int y, double radius) {
        this.displacement_neuron_view = new Displacement_Neuron_View(x,y,radius);
        this.displacement_neuron_view.setLines(this.weights,this.layer);
    }

    @Override
    public void set_Weights(double[] weights) {
        this.weights = new double[this.layer.length];
        for (int i = 0; i < this.layer.length; i++) {
            this.weights[i] = this.layer[i].
                    get_Weights()[this.layer[i].get_Weights().length-1];
        }
    }

    @Override
    public double get_Signal() {
        return SIGNAL;
    }

    @Override
    public double[] get_Weights() {
        return this.weights;
    }

    @Override
    public void set_Signal(double signal) {

    }

    @Override
    public double calculate_Error(int n) {
        return 0;
    }

    @Override
    public double get_Error() {
        return 0;
    }

    @Override
    public void set_Error(double error) {

    }

    @Override
    public void correct_Weights(double learning_Rate) {

    }
}
