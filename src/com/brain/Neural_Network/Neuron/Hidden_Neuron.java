package com.brain.Neural_Network.Neuron;

import com.brain.Neural_Network.View.Neuron_view.Hidden_Neuron_View;
import com.brain.Neural_Network.View.Neuron_view.Neuron_View;

import java.io.Serializable;

public class Hidden_Neuron implements Neuron, Serializable {
    private Neuron[] previous_Layer;
    private double[] weights;
    private double signal;
    private double error;
    private Hidden_Neuron_View hidden_neuron_view;

    public Hidden_Neuron(Neuron[] previous_Layer) {
        this.previous_Layer = previous_Layer;
        this.weights = new double[this.previous_Layer.length];
        for (int i = 0; i < this.weights.length; i++) {
            weights[i] = 2 * Math.random() - 1;
        }
    }

    @Override
    public Neuron[] getLayer() {
        return this.previous_Layer;
    }

    @Override
    public void set_View(int x, int y, double radius) {
        this.hidden_neuron_view = new Hidden_Neuron_View(x,y,radius,this.signal);
        this.hidden_neuron_view.setLines(this.weights,this.previous_Layer);
    }

    @Override
    public Neuron_View get_Neuron_View() {
        return this.hidden_neuron_view;
    }

    @Override
    public void set_Layer(Neuron[] layer) {

    }

    @Override
    public double get_Signal() {
        return this.signal;
    }

    @Override
    public double[] get_Weights() {
        return this.weights;
    }

    @Override
    public void set_Weights(double[] weights) {
        this.weights = weights;
    }

    @Override
    public void set_Signal(double signal) {
        double result = 0;
        for (int i = 0; i < previous_Layer.length; i++) {
            result += previous_Layer[i].get_Signal()*weights[i];
        }
        this.signal = Sigmoid(result);
        this.hidden_neuron_view.change_value(this.signal);
    }

    @Override
    public double calculate_Error(int n) {
        return this.error*weights[n];
    }

    @Override
    public double get_Error() {
        return this.error;
    }

    @Override
    public void set_Error(double error) {
        this.error = error;
        this.hidden_neuron_view.setError(error);
    }

    @Override
    public void correct_Weights(double learning_Rate){
        double deltaW = 0;
        for (int i = 0; i < previous_Layer.length; i++) {
            deltaW += weights[i]*previous_Layer[i].get_Signal();
        }
        deltaW = DerivativeSigmoid(deltaW)*this.error;

        for (int i = 0; i < weights.length; i++) {
            weights[i] -= deltaW*learning_Rate*previous_Layer[i].get_Signal();
        }
    }

    private double Sigmoid(double arg){
        return 1/(1+Math.exp(-1.0*arg));
    }

    private double DerivativeSigmoid(double arg){
        return Sigmoid(arg)*(1-Sigmoid(arg));
    }


}
