package com.brain.Neural_Network.Neuron;

import com.brain.Neural_Network.View.Neuron_view.Neuron_View;

public interface Neuron {
    double get_Signal();
    double[] get_Weights();
    void set_Weights(double[] weights);
    void set_Signal(double signal);
    double calculate_Error(int n);
    double get_Error();
    void  set_Error(double error);
    void correct_Weights(double learning_Rate);
    Neuron_View get_Neuron_View();
    void set_Layer(Neuron[] layer);
    void set_View(int x, int y, double radius);
    Neuron[] getLayer();
}
