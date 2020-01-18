package com.company;

import com.company.Brain_Backpropagation.Neural_Network;

public class Main {

    public static void main(String[] args) {
        Neural_Network nn = new Neural_Network(
                2,
                6,
                3,
                7);

        nn.train(0.02,
                Examples.train,
                Examples.target,
                100000);

        double[][] result = new double[Examples.target.length][];

        for (int i = 0; i < result.length; i++) {
            result[i] = nn.query(Examples.train[i]);
        }

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(Math.round(result[i][j]*10.0)/10.0 + "  ");
            }
            System.out.println();
        }
    }
}
