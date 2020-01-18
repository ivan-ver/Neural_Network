package com.company.Brain_Backpropagation;

import com.company.Brain_Backpropagation.Neuron.Hidden_Neuron;
import com.company.Brain_Backpropagation.Neuron.Inlet_knot;
import com.company.Brain_Backpropagation.Neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

public class Neural_Network {
    private List<Neuron[]> layers = new ArrayList<Neuron[]>();
    private int inlet_Neurons_Number;
    private int outlet_Neurons_Number;
    private int hidden_Layers_Number;
    private int neurons_In_Hidden_Layer;

    public Neural_Network(int inlet_Knots_Number,
                          int outlet_Neurons_Number,
                          int hidden_Layers_Number,
                          int neurons_In_Hidden_Layer){

        this.inlet_Neurons_Number = inlet_Knots_Number;
        this.outlet_Neurons_Number = outlet_Neurons_Number;
        this.hidden_Layers_Number = hidden_Layers_Number;
        this.neurons_In_Hidden_Layer = neurons_In_Hidden_Layer;
        generate_Neurons();
    }

    public double[] query(double[] inletSignal){
        for (int i = 0; i < layers.get(0).length; i++) {
            layers.get(0)[i].set_Signal(inletSignal[i]);
        }
        for (int i = 1; i < layers.size(); i++) {
            for (int j = 0; j < layers.get(i).length; j++) {
                layers.get(i)[j].set_Signal(j);
            }
        }
        double[] result = new double[outlet_Neurons_Number];
        for (int i = 0; i < result.length; i++) {
            result[i] = layers.get(layers.size()-1)[i].get_Signal();
        }
        return result;
    }

    public void train(double learningRate,
                      double[][] trainMatrix,
                      double[][] targetMatrix,
                      int epochs){

        for (int epoch = 0; epoch < epochs; epoch++) {
            if (epoch%1000 == 0){
                System.out.print(epoch + " - ");
                for (int i = 0; i < trainMatrix.length; i++) {
                    System.out.print(Math_Operations.mseLoss(query(trainMatrix[i]),targetMatrix[i])+"  ");
                }
                System.out.println();
            }

            for (int i = 0; i < trainMatrix.length; i++) {
                for (int j = 0; j < layers.get(layers.size() - 1).length; j++) {
                    layers.get(layers.size() - 1)[j].set_Error(query(trainMatrix[i])[j]-targetMatrix[i][j]);
                }
                for (int j = layers.size()-2; j >= 1; j--) {
                    for (int k = 0; k < layers.get(j).length; k++) {
                        double er = 0;
                        for (int l = 0; l < layers.get(j+1).length; l++) {
                            er += layers.get(j+1)[l].get_Error(k);
                        }
                        layers.get(j)[k].set_Error(er);
                    }
                }
                for (int k = layers.size()-1; k >= 1; k--) {
                    for (int l = 0; l < layers.get(k).length; l++) {
                        layers.get(k)[l].correct_Weights(learningRate);
                    }
                }
            }
        }
    }




    private void generate_Neurons() {
        Neuron[] inlet_knots = new Inlet_knot[this.inlet_Neurons_Number];
        for (int i = 0; i < this.inlet_Neurons_Number; i++) {
            inlet_knots[i] = new Inlet_knot();
        }
        layers.add(inlet_knots);
        for (int i = 0; i < hidden_Layers_Number; i++) {
            Neuron[] hidden_layer = new Hidden_Neuron[i != hidden_Layers_Number - 1 ?
                    this.neurons_In_Hidden_Layer :
                    this.outlet_Neurons_Number];
            for (int j = 0; j < hidden_layer.length; j++) {
                hidden_layer[j] = new Hidden_Neuron(layers.get(i).length, layers.get(i));
            }
            layers.add(hidden_layer);
        }
    }

}