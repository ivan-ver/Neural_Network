package com.company.Brain_Backpropagation;

public class Math_Operations {
    public static double Sigmoid(double arg){
        return 1/(1+Math.pow(Math.E,(-1)*arg));
    }

    public static double DerivativeSigmoid(double arg){
        return Sigmoid(arg)*(1-Sigmoid(arg));
    }

    static double mseLoss(double[] target, double[] result){
        double r = 0;
        for (int i = 0; i < target.length; i++) {
            r += Math.pow((target[i]-result[i]),2);
        }
        return r/target.length;
    }
}
