package com.animals;

public class Main_geometry {
    public static final int windowWidth = 1500;
    public static final int windowHeight = 700;
    public static final int agentDiameter = 30;
    public static final int foodDiameter = 10;

    public static final double Xcenter =  windowWidth/2;
    public static final double Ycenter =  0.7*windowHeight;

    public static double[] getRandomCoordinates(){
        double[] res = new double[2];
        res[0] = 20+(int)(windowWidth-200)*Math.random();
        res[1] = 20+(int)(windowHeight-200)*Math.random();
        return res;

    }
}
