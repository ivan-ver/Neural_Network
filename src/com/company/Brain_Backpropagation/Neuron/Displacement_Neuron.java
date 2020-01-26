package com.company.Brain_Backpropagation.Neuron;

public class Displacement_Neuron implements Neuron {
    private double signal;

    public Displacement_Neuron() {
        this.signal = 1.0;
    }

    @Override
    public double get_Signal() {
        return this.signal;
    }

    @Override
    public void set_Signal(double signal) {

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
