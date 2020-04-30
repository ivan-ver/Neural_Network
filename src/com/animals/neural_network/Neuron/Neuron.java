package com.animals.neural_network.Neuron;


public interface Neuron {
    double get_Signal();
    void set_Signal(double signal);

    double calculate_Error(int n);
    void  set_Error(double error);

    Neuron[] getLayer();
    void set_Layer(Neuron[] layer);

    void correct_Weights(double learning_Rate);
}
