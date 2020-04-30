package com.animals;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Field extends JComponent implements Runnable{


    Agent[] agents = new Agent[10];
    Food food;



    public Field(){
        this.food = new Food(Main_geometry.Xcenter,Main_geometry.Ycenter);
        double R = 280;

        for (int i = 0; i < agents.length; i++) {
            double x = Main_geometry.Xcenter + R*Math.cos(Math.toRadians(-180/9)*i);
            double y = Main_geometry.Ycenter + R*Math.sin(Math.toRadians(-180/9)*i);
            agents[i] = new Agent(x,y,i);
        }



        Arrays.stream(this.agents).forEach(agent -> agent.findFood(
                this.food.getEllipse2D().getCenterX(),
                this.food.getEllipse2D().getCenterY()
        ));


        (new Thread(this)).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Arrays.stream(this.agents).forEach(agent -> agent.drawAgent(g));
        this.food.draw(g);
    }

    @Override
    public void run() {
        while (true){
            try {
                Arrays.stream(this.agents).forEach(agent -> agent.findFood(
                        this.food.getEllipse2D().getCenterX(),
                        this.food.getEllipse2D().getCenterY()
                ));
                Arrays.stream(this.agents).filter(Agent::isLive).forEach(Agent::move);

                Thread.sleep(100);
                super.repaint();
                if (Arrays.stream(agents).allMatch(agent -> !agent.isLive())){
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Arrays.stream(agents).forEach(agent -> System.out.println(agent.getNumber() + " -" + agent.getFitness()));
    }
}
