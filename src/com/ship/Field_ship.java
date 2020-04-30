package com.ship;

import com.animals.Main_geometry;

import javax.swing.*;
import java.awt.*;

public class Field_ship extends JComponent implements Runnable {
    Ship ship1;
    Ship ship2;
    Ship ship3;


    public Field_ship(){
        this.ship1 = new Ship(0,Math.random()* Main_geometry.windowHeight-400);
        this.ship2 = new Ship(0,Math.random()*Main_geometry.windowHeight-400);
        this.ship3 = new Ship(0,Math.random()*Main_geometry.windowHeight-400);
        (new Thread(this)).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
       this.ship1.draw(g);
       this.ship2.draw(g);
       this.ship3.draw(g);
    }

    @Override
    public void run() {
        try {

            while (true){
                do {
                    this.ship1.move();
                    this.ship2.move();
                    this.ship3.move();

                    Thread.sleep(20);
                    super.repaint();
                }while (this.ship1.getRectangle2D().getX()!=Main_geometry.windowWidth);

                this.ship1 = new Ship(30,Math.random()*Main_geometry.windowHeight);
                this.ship2 = new Ship(30,Math.random()*Main_geometry.windowHeight);
                this.ship3 = new Ship(30,Math.random()*Main_geometry.windowHeight);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
