package com.animals.neural_network.Neuron;



import java.io.Serializable;

public class Inlet_Knot implements Neuron, Serializable {
    private double signal;

    public Inlet_Knot(){ }

    //**********************************************************************//

    @Override
    public void set_Layer(Neuron[] layer) { }

    @Override
    public Neuron[] getLayer() {
        return null;
    }


    @Override
    public double get_Signal() {
        return this.signal;
    }

    @Override
    public void set_Signal(double signal) {
        this.signal = signal;
    }

    @Override
    public double calculate_Error(int n) {
        return 0;
    }

    @Override
    public void set_Error(double error) { }

    @Override
    public void correct_Weights(double learning_Rate) { }

}
