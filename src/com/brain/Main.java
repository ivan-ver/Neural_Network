package com.brain;

import com.brain.Neural_Network.Neural_Network;
import com.brain.Neural_Network.Neuron.Inlet_Knot;
import com.brain.Neural_Network.Neuron.Neuron;
import com.brain.Neural_Network.View.Geometry;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        Neural_Network nn = new Neural_Network("Simple_NN")
                .generate_simple_config_Neurons(
                        Examples.trainXor[0].length,
                        Examples.targetXor[0].length,
                        2,
                        6);




        JFrame jFrame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(dimension.width/2 - Geometry.windowWidth/2,
                dimension.height/2 - Geometry.windowHeight/2,
                Geometry.windowWidth,
                Geometry.windowHeight);

        jFrame.setTitle("Neural Network");
        jFrame.add(nn.getView());




//        nn.train(0.05,
//                Examples.trainInputs,
//                Examples.trainOutputs,
//                80000);


//        double[][] result = new double[Examples.testOutput.length][];
//
//        for (int i = 0; i < result.length; i++) {
//            result[i] = nn.query(Examples.testInput[i]);
//        }

//        for (double[] doubles : result) {
//            for (double aDouble : doubles) {
//                System.out.print(Math.round(aDouble * 100000.0) / 100000.0 + " ");
////                System.out.print(aDouble + " ");
//            }
//            System.out.println();
//        }

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
