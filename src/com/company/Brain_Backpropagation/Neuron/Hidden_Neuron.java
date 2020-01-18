package com.company.Brain_Backpropagation.Neuron;

import com.company.Brain_Backpropagation.Math_Operations;

public class Hidden_Neuron implements Neuron {
    private Neuron[] previous_Layer;
    private double[] weights;
    private double signal = 0;
    private double error = 0;

    public Hidden_Neuron(int prevLayerNeuronsNumb, Neuron[] previous_Layer) {
        generateWeights(prevLayerNeuronsNumb);
        this.previous_Layer = previous_Layer;
    }

    private void generateWeights(int prevLayerNeuronsNumb){
        weights = new double[prevLayerNeuronsNumb];
        for (int i = 0; i < prevLayerNeuronsNumb; i++) {
            weights[i] = Math.random()*2-1;
        }

    }

    @Override
    public double get_Signal() {
        return this.signal;
    }

    @Override
    public void set_Signal(double signal) {
        double result = 0;
        for (int i = 0; i < previous_Layer.length; i++) {
            result += previous_Layer[i].get_Signal()*weights[i];
        }
        this.signal = Math_Operations.Sigmoid(result);
    }

    @Override
    public double get_Error(int n) {
        return this.error*weights[n];
    }

    @Override
    public void set_Error(double error) {
        this.error = error;
    }

    @Override
    public void correct_Weights(double learning_Rate){
        double deltaW = 0;
        for (int i = 0; i < previous_Layer.length; i++) {
            deltaW += weights[i]*previous_Layer[i].get_Signal();
        }
        deltaW = Math_Operations.DerivativeSigmoid(deltaW)*this.error;

        for (int i = 0; i < weights.length; i++) {
            weights[i] -= deltaW*learning_Rate*previous_Layer[i].get_Signal();
        }
    }


}
