package com.brain;

import com.brain.Brain_Backpropagation.Neural_Network;

public class Main {

    public static void main(String[] args) throws Exception {

        Neural_Network nn = new Neural_Network("Simple_NN")
                .generate_simple_config_Neurons(
                        Examples.trainInputs[0].length,
                        Examples.trainOutputs[0].length,
                        3,
                        6);

        nn.train(0.05,
                Examples.trainInputs,
                Examples.trainOutputs,
                80000);


        double[][] result = new double[Examples.testOutput.length][];

        for (int i = 0; i < result.length; i++) {
            result[i] = nn.query(Examples.testInput[i]);
        }

        for (double[] doubles : result) {
            for (double aDouble : doubles) {
                System.out.print(Math.round(aDouble * 100000.0) / 100000.0 + " ");
//                System.out.print(aDouble + " ");
            }
            System.out.println();
        }

//        double[][] res = Math_Operations.divider(Examples.trainInputs, 10);
//        System.out.println();

//        simple_nn.save();
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("brain"));
//        Neural_Network brain2 = (Neural_Network)objectInputStream.readObject();

//        System.out.println();

//        Neural_Network nn = new Neural_Network("pyramid")
//                .generate_pyramid_structure(
//                        Examples.trainInputs[0].length,
//                        Examples.trainOutputs[0].length,
//                        5);

    }
}
