package com.ship;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Ship {
    private Rectangle2D rectangle2D;
    private Color color = Color.green;
    private double X;
    private double Y;

    public Rectangle2D getRectangle2D() {
        return this.rectangle2D;
    }

    public Ship(double x, double y) {
        this.X = x;
        this.Y = y;
        setShip();
    }
    private void setShip(){
        this.rectangle2D = new Rectangle2D.Double(this.X,this.Y,120, 50);
    }

    public void draw(Graphics g) {
        Graphics2D graph = (Graphics2D)g;
        graph.setPaint(color);

        graph.draw(this.rectangle2D);
        graph.fill(this.rectangle2D);

//        Graphics2D value = (Graphics2D)g;
//        value.setPaint(Color.BLACK);
//        value.setFont(new Font("Arial",Font.BOLD,15));
//        value.drawString(String.valueOf(Math.round(this.rectangle2D.getCenterX())),
//                (int)this.rectangle2D.getCenterX()-20,
//                (int)this.rectangle2D.getCenterY()-8);
//
//        value.drawString(String.valueOf(Math.round(this.rectangle2D.getCenterY())),
//                (int)this.rectangle2D.getCenterX()-20,
//                (int)this.rectangle2D.getCenterY()+12);

    }

    public void move(){
        this.X +=10;
        setShip();
    }
}
