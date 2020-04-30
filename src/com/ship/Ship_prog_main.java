package com.ship;

import com.animals.Main_geometry;

import javax.swing.*;
import java.awt.*;

public class Ship_prog_main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(dimension.width/2 - Main_geometry.windowWidth/2,
                dimension.height/2 - Main_geometry.windowHeight/2,
                Main_geometry.windowWidth,
                Main_geometry.windowHeight);

        jFrame.setTitle("Neural Network");
        jFrame.getContentPane().setBackground(new Color(67, 67, 67));
        jFrame.add(new Field_ship());

    }
}
