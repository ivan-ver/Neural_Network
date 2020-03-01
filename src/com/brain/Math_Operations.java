package com.brain;

public class Math_Operations {

    static double mseLoss(double[] target, double[] result){
        double r = 0;
        for (int i = 0; i < target.length; i++) {
            r += Math.pow((target[i]-result[i]),2);
        }
        return r/target.length;
    }


}
