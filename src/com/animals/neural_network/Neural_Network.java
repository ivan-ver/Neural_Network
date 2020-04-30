package com.animals.neural_network;

import com.animals.neural_network.Neuron.Displacement_Neuron;
import com.animals.neural_network.Neuron.Hidden_Neuron;
import com.animals.neural_network.Neuron.Inlet_Knot;
import com.animals.neural_network.Neuron.Neuron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Neural_Network{
    private List<Neuron[]> layers = new ArrayList<>();

    public Neural_Network(int inlet_knots_number,
                          int outlet_neurons_number,
                          Neuron[]... layers){
        Neuron[] inlet_knot = new Neuron[inlet_knots_number + 1];
        for (int i = 0; i < inlet_knots_number; i++) {
            inlet_knot[i] = new Inlet_Knot();
        }
        inlet_knot[inlet_knots_number] = new Displacement_Neuron();
        this.layers.add(inlet_knot);

        Collections.addAll(this.layers, layers);

        Neuron[] outlet_layer = new Neuron[outlet_neurons_number];
        for (int i = 0; i < outlet_neurons_number; i++) {
            outlet_layer[i] = new Hidden_Neuron();
        }
        this.layers.add(outlet_layer);
    }

    public Neural_Network compile() {
        for (int i = 1; i < layers.size(); i++) {
            for (Neuron neuron : layers.get(i)) {
                neuron.set_Layer(layers.get(i - 1));
            }
        }
        return this;
    }



    public double[] query(double[] inletSignal){
        for (int i = 0; i < layers.get(0).length - 1; i++) {
            layers.get(0)[i].set_Signal(inletSignal[i]);
        }
        for (int i = 1; i < layers.size(); i++) {
            for (int j = 0; j < layers.get(i).length; j++) {
                layers.get(i)[j].set_Signal(0);
            }
        }
        double[] result = new double[this.layers.get(this.layers.size()-1).length];
        for (int i = 0; i < result.length; i++) {
            result[i] = layers.get(layers.size()-1)[i].get_Signal();
        }
        return result;
    }





    public void trainBackPropagation(double learningRate,
                      double[][] trainMatrix,
                      double[][] targetMatrix){
        for (int ep = 0; ep < 50000; ep++) {



            for (int i = 0; i < trainMatrix.length; i++) {
                for (int j = 0; j < layers.get(layers.size() - 1).length; j++) {
                    layers.get(layers.size() - 1)[j].set_Error(query(trainMatrix[i])[j]-targetMatrix[i][j]);
                }

                for (int j = layers.size()-2; j >= 1; j--) {
                    for (int k = 0; k < layers.get(j).length; k++) {
                        double error = 0;
                        for (int l = 0; l < layers.get(j+1).length; l++) {
                            error += layers.get(j+1)[l].calculate_Error(k);
                        }
                        layers.get(j)[k].set_Error(error);
                    }
                }

                layers.forEach(layer -> Arrays.stream(layer).
                        forEach(neuron -> neuron.correct_Weights(learningRate)));
            }

        }
    }
}
