package com.brain.Neural_Network.View;
import com.brain.Examples;
import com.brain.Neural_Network.Neural_Network;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Neural_Network_view extends JComponent implements Runnable{
    private Neural_Network neural_network;
    public Neural_Network_view(Neural_Network neural_network) {
        this.neural_network = neural_network;
        (new Thread(this)).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        neural_network.getLayers().forEach((layer) -> Arrays.stream(layer).
                forEach((neuron) -> neuron.
                        get_Neuron_View().draw_line(g)));

        neural_network.getLayers().forEach((layer) -> Arrays.stream(layer).
                forEach((neuron) -> neuron.
                        get_Neuron_View().draw_ellipse(g)));
    }

    @Override
    public void run() {
        while (true){
            try {

                for (double[] matrix : Examples.trainXor) {
                    neural_network.query(matrix);
                    super.repaint();
                    Thread.sleep(1);
                }

                this.neural_network.train(
                        0.03,
                        Examples.trainXor,
                        Examples.targetXor
                );


                super.repaint();
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
