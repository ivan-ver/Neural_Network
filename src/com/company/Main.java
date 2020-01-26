package com.company;

import com.company.Brain_Backpropagation.Neural_Network;
import com.company.Brain_Backpropagation.Neuron.Displacement_Neuron;
import com.company.Brain_Backpropagation.Neuron.Inlet_knot;
import com.company.Brain_Backpropagation.Neuron.Neuron;

public class Main {

    public static void main(String[] args) {
        Neural_Network nn = new Neural_Network(
                Examples.train[0].length,
                Examples.target[0].length,
                2,
                10);

//        nn.query(Examples.trainXor[0]);

        nn.train(0.03,
                Examples.train,
                Examples.target,
                90000);

        double[][] result = new double[Examples.target.length][];

        for (int i = 0; i < result.length; i++) {
            result[i] = nn.query(Examples.train[i]);
        }

        for (double[] doubles : result) {
            for (double aDouble : doubles) {
                System.out.print(Math.round(aDouble * 10.0) / 10.0 + " ");
            }
            System.out.println();
        }


    }
}
