package com.brain.Neural_Network;

import com.brain.Neural_Network.Neuron.Displacement_Neuron;
import com.brain.Neural_Network.Neuron.Hidden_Neuron;
import com.brain.Neural_Network.Neuron.Inlet_Knot;
import com.brain.Neural_Network.Neuron.Neuron;
import com.brain.Neural_Network.View.Geometry;
import com.brain.Neural_Network.View.Neural_Network_view;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Neural_Network implements Serializable {
    private String name;
    private List<Neuron[]> layers = new ArrayList<Neuron[]>();
    private JComponent view;

    public Neural_Network(String name){
        this.name = name;
    }

    public JComponent getView() {
        return view;
    }

    public List<Neuron[]> getLayers() {
        return layers;
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
                      double[][] targetMatrix){

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

                for (int j = 1; j < layers.size(); j++) {
                    for (int k = 0; k < layers.get(j).length; k++) {
                        layers.get(j)[k].correct_Weights(learningRate);
                    }
                }
            }
            layers.forEach(layer -> Arrays.stream(layer).
                    filter(neuron -> neuron instanceof Hidden_Neuron).
                    forEach(neuron -> neuron.get_Neuron_View().setLines(
                            neuron.get_Weights(),neuron.getLayer()
                    )));
    }

    public Neural_Network generate_simple_config_Neurons(int inlet_Knots_Number,
                                                int outlet_Neurons_Number,
                                                int hidden_Layers_Number,
                                                int neurons_In_Hidden_Layer) {
        //входные узлы
        Neuron[] inlet_knots = new Neuron[inlet_Knots_Number + 1];
        for (int i = 0; i < inlet_Knots_Number; i++) {
            inlet_knots[i] = new Inlet_Knot.Builder().
                    set_view(Geometry.windowWidth/(hidden_Layers_Number + 2)-Geometry.neuron_radius,
                            Geometry.windowHeight/(inlet_Knots_Number + 1)*(i + 1)-Geometry.neuron_radius,
                            Geometry.neuron_radius).build();
        }
        inlet_knots[inlet_Knots_Number] = new Displacement_Neuron();
        this.layers.add(inlet_knots);

        //скрытые слои
        for (int i = 0; i < hidden_Layers_Number; i++) {
            Neuron[] hidden_layer = new Neuron[i != hidden_Layers_Number - 1 ? neurons_In_Hidden_Layer + 1 : neurons_In_Hidden_Layer];
            for (int j = 0; j < hidden_layer.length; j++) {
                Neuron[] p_l = layers.get(i);
                Neuron hidden_neuron = new Hidden_Neuron(p_l);
                hidden_neuron.set_View(
                        (int) (((Geometry.windowWidth/(hidden_Layers_Number + 2 + 1))*(i + 1)-Geometry.neuron_radius) + Geometry.windowWidth/(hidden_Layers_Number+2)),
                        (int) ((Geometry.windowHeight/(neurons_In_Hidden_Layer + 1))*(j + 1) - Geometry.neuron_radius),
                        Geometry.neuron_radius);
                hidden_layer[j] = hidden_neuron;
            }
            if (i != hidden_Layers_Number - 1) {
                hidden_layer[hidden_layer.length - 1] = new Displacement_Neuron();
            }

            layers.add(hidden_layer);
        }
        //выходной слой
        Neuron[] outlet_layer = new Hidden_Neuron[outlet_Neurons_Number];
        for (int i = 0; i < outlet_layer.length; i++) {
            Neuron[] p_l = layers.get(layers.size() - 1);
            Neuron hidden_neuron = new Hidden_Neuron(p_l);
            hidden_neuron.set_View(
                    Geometry.windowWidth - Geometry.windowWidth/(hidden_Layers_Number+2) + (int)Geometry.neuron_radius,
                    (int)(Geometry.windowHeight/(outlet_Neurons_Number + 1)*(i+1)-Geometry.neuron_radius),
                    Geometry.neuron_radius);
            outlet_layer[i] = hidden_neuron;
        }
        layers.add(outlet_layer);
        //заполнение нейронов смещения
        for (int i = 0; i < layers.size() - 2; i++) {
            layers.get(i)[layers.get(i).length - 1].set_Layer(
                    Arrays.stream(layers.get(i + 1)).
                            filter((x) -> x instanceof Hidden_Neuron).
                            toArray(Neuron[]::new)
            );
            layers.get(i)[layers.get(i).length - 1].set_Weights(null);
        }

        for (int i = 0; i < layers.size() - 2; i++) {
            Neuron[] layer = layers.get(i);
            double x = layers.get(i + 1)[0].get_Neuron_View().getCircle().getX();
            double y = layers.get(1)[0].get_Neuron_View().getCircle().getY();
            layer[layer.length - 1].set_View(
                    (int)x - 100,
                    (int)y - 60,
                    Geometry.neuron_radius);
        }

        this.view = new Neural_Network_view(this);
        return this;
    }

    public void save() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("brain_"+this.name));
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }
}
