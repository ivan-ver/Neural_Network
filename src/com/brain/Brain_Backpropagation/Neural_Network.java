package com.brain.Brain_Backpropagation;

import com.brain.Brain_Backpropagation.Neuron.Displacement_Neuron;
import com.brain.Brain_Backpropagation.Neuron.Hidden_Neuron;
import com.brain.Brain_Backpropagation.Neuron.Inlet_knot;
import com.brain.Brain_Backpropagation.Neuron.Neuron;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Neural_Network implements Serializable {
    private String name;
    private List<Neuron[]> layers = new ArrayList<Neuron[]>();

    public Neural_Network(String name){
        this.name = name;
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

    public void train(double learningRate,
                      double[][] trainMatrix,
                      double[][] targetMatrix,
                      int epochs){

        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < trainMatrix.length; i++) {

                for (int j = 0; j < layers.get(layers.size() - 1).length; j++) {
                    layers.get(layers.size() - 1)[j].set_Error(query(trainMatrix[i])[j]-targetMatrix[i][j]);
                }

                for (int j = layers.size()-2; j >= 1; j--) {
                    for (int k = 0; k < layers.get(j).length; k++) {
                        double error = 0;
                        for (int l = 0; l < layers.get(j+1).length; l++) {
                            error += layers.get(j+1)[l].get_Error(k);
                        }
                        layers.get(j)[k].set_Error(error);
                    }
                }

                for (int j = 1; j < layers.size(); j++) {
                    for (int k = 0; k < layers.get(j).length; k++) {
                        layers.get(j)[k].correct_Weights(learningRate);
                    }
                }
            }
        }
    }

    public Neural_Network generate_simple_config_Neurons(int inlet_Knots_Number,
                                                int outlet_Neurons_Number,
                                                int hidden_Layers_Number,
                                                int neurons_In_Hidden_Layer) {
        //входные узлы
        Neuron[] inlet_knots = new Neuron[inlet_Knots_Number+1];
        for (int i = 0; i < inlet_Knots_Number; i++) {
            inlet_knots[i] = new Inlet_knot();
        }
        inlet_knots[inlet_Knots_Number] = new Displacement_Neuron();
        layers.add(inlet_knots);

        //скрытые слои
        for (int i = 0; i < hidden_Layers_Number; i++) {
            Neuron[] hidden_layer = new Neuron[neurons_In_Hidden_Layer+1];
            for (int j = 0; j < hidden_layer.length; j++) {
                hidden_layer[j] = new Hidden_Neuron(layers.get(i));
            }
            hidden_layer[neurons_In_Hidden_Layer] = new Displacement_Neuron();
            layers.add(hidden_layer);
        }
        //выходной слой
        Neuron[] outlet_layer = new Neuron[outlet_Neurons_Number];
        for (int i = 0; i < outlet_Neurons_Number; i++) {
            outlet_layer[i] = new Hidden_Neuron(layers.get(layers.size()-1));
        }
        layers.add(outlet_layer);
        return this;
    }

    public Neural_Network generate_pyramid_structure(int inlet_Knots_Number,
                                                     int outlet_Neurons_Number,
                                                     int max_neuron_Number){
        //входные узлы
        Neuron[] inlet_knots = new Neuron[inlet_Knots_Number + 1];
        for (int i = 0; i < inlet_Knots_Number; i++) {
            inlet_knots[i] = new Inlet_knot();
        }
        inlet_knots[inlet_Knots_Number] = new Displacement_Neuron();
        layers.add(inlet_knots);

        //скрытые слои
        for (int i = 0; i < max_neuron_Number - inlet_Knots_Number + 1; i++) {
            Neuron[] hidden_layer = new Neuron[inlet_Knots_Number + i+1];
            for (int j = 0; j < hidden_layer.length; j++) {
                hidden_layer[j] = new Hidden_Neuron(layers.get(i));
            }
            hidden_layer[hidden_layer.length-1] = new Displacement_Neuron();
            layers.add(hidden_layer);
        }

        for (int i = max_neuron_Number; i >=  outlet_Neurons_Number + 2; i--) {
            Neuron[] hidden_layer = new Neuron[i];
            for (int j = 0; j < hidden_layer.length; j++) {
                hidden_layer[j] = new Hidden_Neuron(layers.get(layers.size() - 1));
            }
            hidden_layer[hidden_layer.length-1] = new Displacement_Neuron();
            layers.add(hidden_layer);
        }

        //выходной слой
        Neuron[] outlet_layer = new Neuron[outlet_Neurons_Number];
        for (int i = 0; i < outlet_Neurons_Number; i++) {
            outlet_layer[i] = new Hidden_Neuron(layers.get(layers.size()-1));
        }
        layers.add(outlet_layer);
        return this;
    }

    public void save() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("brain_"+this.name));
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }

}
