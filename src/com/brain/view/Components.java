package com.brain.view;

import com.brain.view.primitives.SimpleLine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Components extends JComponent implements Runnable{
    private List<DrawNeuron[]> layers;
//    SimpleLine line;
    private double radius = 50;
    private int inlet_neuron_numb = 2;
    private int outlet_neuron_numb = 1;
    private int hidden_neuron_numb = 4;
    private int hidden_layers_numb = 5;

    public Components() {
//        line = new SimpleLine(250,320,650,782);
        layers = new ArrayList<>();

        //Входной слой
        DrawNeuron[] inlet_l = new DrawNeuron[inlet_neuron_numb];
        for (int i = 0; i < inlet_neuron_numb; i++) {
            inlet_l[i] = new DrawNeuron((double)((view.windowWidth/(hidden_layers_numb+2))-radius)
                    ,(double)(view.windowHeight/(inlet_neuron_numb+1))*(i+1)-radius);
        }
        this.layers.add(inlet_l);

        //Скрытые слои
        for (int i = 0; i < hidden_layers_numb; i++) {
            DrawNeuron[] hidden_l = new DrawNeuron[hidden_neuron_numb];
            for (int j = 0; j < hidden_neuron_numb; j++) {
                DrawNeuron dn = new DrawNeuron((double)((view.windowWidth/(hidden_layers_numb+2+1))*(i+1)-radius)+(double)view.windowWidth/(hidden_layers_numb+2),
                        (double)((view.windowHeight/(hidden_neuron_numb+1))*(j+1)-radius));
                dn.setPrevious_layer(this.layers.get(i));
                hidden_l[j] = dn;
                dn.setLines();
            }
            this.layers.add(hidden_l);
        }

        //Выходной слой
        DrawNeuron[] outlet_l = new DrawNeuron[outlet_neuron_numb];
        for (int i = 0; i < outlet_neuron_numb; i++) {
            DrawNeuron dn = new DrawNeuron((double)(view.windowWidth-view.windowWidth/(hidden_layers_numb+2)+radius),
                    (double)((view.windowHeight/(outlet_neuron_numb + 1))*(i+1)-radius));
            dn.setPrevious_layer(this.layers.get(this.layers.size()-1));
            outlet_l[i] = dn;
            dn.setLines();
        }
        this.layers.add(outlet_l);



        (new Thread(this)).start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        Graphics2D back = (Graphics2D)g;
//        back.setStroke(new BasicStroke(10f));
//        back.setPaint(line.getColor());
//        back.draw(line);



        for (int i = 1; i < this.layers.size(); i++) {
            for (DrawNeuron draw_neuron : this.layers.get(i)) {
                draw_neuron.draw_line(g);
            }
        }

        for (DrawNeuron[] layer : this.layers) {
            for (DrawNeuron draw_neuron : layer) {
                draw_neuron.draw_ellipse(g);
            }
        }


    }

    @Override
    public void run() {
        while (true){
            try {
                for (int i = 1; i < layers.size(); i++) {
                    for (DrawNeuron n : layers.get(i)) {
                        for (SimpleLine line : n.getLines()) {
                            line.changeColorAndThickness();
                        }
                    }
                }
//                line.changeColor();
                super.repaint();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
