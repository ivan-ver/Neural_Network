package com.animals;

import com.animals.neural_network.Neural_Network;
import com.animals.neural_network.Neuron.Neuron_Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Agent extends JComponent {
    private int number;

    private Ellipse2D ellipse2D;
    private Line2D line;
    private Color color = Color.WHITE;

    private int energy;
    private boolean isLive;

    private double X;
    private double Y;

    private double X0;
    private double Y0;

    private double X_to_Food;
    private double Y_to_Food;

    private double FITNESS;

    private double X1_to_wall;
    private double X2_to_wall;
    private double Y1_to_wall;
    private double Y2_to_wall;

    private double Vector = 1;

    private Neural_Network brain;


    public Agent(double x, double y, int number) {
        this.number = number;

        this.X0 = x;
        this.Y0 = y;

        this.X = x;
        this.Y = y;

        this.X1_to_wall = x;
        this.X2_to_wall = Main_geometry.windowWidth - x;

        this.Y1_to_wall = y;
        this.Y2_to_wall = Main_geometry.windowHeight - y;


        this.brain = new Neural_Network(6,1,
                    Neuron_Factory.get_new_layer(10),
                    Neuron_Factory.get_new_layer(12),
                    Neuron_Factory.get_new_layer(12))
                .compile();

        this.energy = 256;
        this.isLive = true;
        setAgent();
    }
    private void setAgent(){
        this.ellipse2D = new Ellipse2D.Double(this.X-Main_geometry.agentDiameter/2,this.Y-Main_geometry.agentDiameter/2,
                Main_geometry.agentDiameter, Main_geometry.agentDiameter);

    }

    public void findFood(double x, double y){
        this.X_to_Food = Math.abs((x) - this.X);
        this.Y_to_Food = Math.abs((y) - this.Y);

        this.line = new Line2D.Double(this.X, this.Y,x,y);

    }


    public void drawAgent(Graphics g) {
        Graphics2D graph = (Graphics2D)g;
        graph.setPaint(color);

        graph.draw(this.ellipse2D);
        graph.fill(this.ellipse2D);

        Graphics2D value = (Graphics2D)g;
        value.setPaint(Color.WHITE);
        value.setFont(new Font("Arial",Font.BOLD,15));
        value.drawString(String.valueOf(Math.round(this.number)),
                (int)this.ellipse2D.getCenterX()-20,
                (int)this.ellipse2D.getCenterY()-8);

        Graphics2D lineGraph = (Graphics2D)g;
        lineGraph.setPaint(color);
        graph.draw(this.line);
    }

    public void move(){

            if (this.X <= Main_geometry.agentDiameter/2 | this.Y <= Main_geometry.agentDiameter/2 |
                    this.X + Main_geometry.agentDiameter/2 >= Main_geometry.windowWidth |
                    this.Y + Main_geometry.agentDiameter/2 >= Main_geometry.windowHeight
            ){
                this.isLive = false;
                this.color = Color.BLACK;
                setAgent();

            }else
                if (this.energy > 0){
                double[] inletSignal = {
                        this.X_to_Food,
                        this.Y_to_Food,
                        this.X1_to_wall,
                        this.X2_to_wall,
                        this.Y1_to_wall,
                        this.Y2_to_wall
                };

                double ang = this.brain.query(inletSignal)[0] * 360;

                this.Y += Math.sin(Math.toRadians(ang)) * this.Vector;
                this.X += Math.cos(Math.toRadians(ang)) * this.Vector;
                this.energy--;
                this.color = new Color(energy, energy, 0);


                setAgent();
            } else {
                this.isLive = false;




                double distanceToFood = Math.sqrt(Math.pow(this.X_to_Food,2) + Math.pow(Y_to_Food,2));

                this.FITNESS =
                        Math.pow(distanceToFood,2) -
                        Math.abs(this.X2_to_wall - this.X1_to_wall) -
                        Math.abs(this.Y2_to_wall - this.Y1_to_wall) -
                        this.energy;

                this.color = Color.BLACK;
                setAgent();
            }


    }

    public boolean isLive() {
        return this.isLive;
    }

    public double getFitness(){
        return this.FITNESS;
    }

    public int getNumber() {
        return number;
    }
}
