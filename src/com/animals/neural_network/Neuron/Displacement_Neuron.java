package com.animals.neural_network.Neuron;


import java.io.Serializable;

public class Displacement_Neuron implements Neuron, Serializable {
    private double SIGNAL;

    public Displacement_Neuron() {
        this.SIGNAL = 1.0;
    }


    @Override
    public Neuron[] getLayer() {
        return null;
    }


    @Override
    public void set_Layer(Neuron[] layer) {

    }

    @Override
    public double get_Signal() {
        return SIGNAL;
    }



    @Override
    public void set_Signal(double signal) {

    }

    @Override
    public double calculate_Error(int n) {
        return 0;
    }


    @Override
    public void set_Error(double error) {

    }

    @Override
    public void correct_Weights(double learning_Rate) {

    }
}
