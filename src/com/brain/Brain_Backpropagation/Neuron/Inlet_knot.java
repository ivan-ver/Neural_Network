package com.brain.Brain_Backpropagation.Neuron;

import java.io.Serializable;

public class Inlet_knot implements Neuron, Serializable {
    private double signal;

    public Inlet_knot() {
    }

    public double get_Signal() {
        return this.signal;
    }

    public void set_Signal(double signal) {
        this.signal = signal;
    }

    @Override
    public double get_Error(int n) {
        return 0;
    }

    @Override
    public void set_Error(double error) {

    }

    @Override
    public void correct_Weights(double learning_Rate) {

    }


}
