package com.brain.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class view {
    static int windowWidth = 1400;
    static int windowHeight = 800;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(dimension.width/2 - windowWidth/2,
                dimension.height/2 - windowHeight/2,
                windowWidth,windowHeight);

        jFrame.setTitle("Neural Network");
        jFrame.add(new Components());
    }
}



