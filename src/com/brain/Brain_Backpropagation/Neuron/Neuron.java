package com.brain.Brain_Backpropagation.Neuron;

public interface Neuron {
    double get_Signal();
    void set_Signal(double signal);
    double get_Error(int n);
    void  set_Error(double error);
    void correct_Weights(double learning_Rate);
}
