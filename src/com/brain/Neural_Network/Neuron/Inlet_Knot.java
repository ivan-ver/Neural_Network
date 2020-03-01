package com.brain.Neural_Network.Neuron;

import com.brain.Neural_Network.View.Neuron_view.Inlet_Knot_View;
import com.brain.Neural_Network.View.Neuron_view.Neuron_View;

import java.io.Serializable;

public class Inlet_Knot implements Neuron, Serializable {
    private double signal;
    private Inlet_Knot_View inlet_knot_view;

    public Inlet_Knot(Builder builder){
        this.inlet_knot_view = builder.inlet_knot_view;
        this.signal = builder.signal;
    }

    public static class Builder{
        private Inlet_Knot_View inlet_knot_view;
        private double signal;

        public Builder(){}


        public Builder set_view(double x, double y, double radius){
            this.inlet_knot_view = new Inlet_Knot_View(x,y,radius,this.signal);
            return this;
        }

        public Inlet_Knot build(){
            return new Inlet_Knot(this);
        }
    }

    @Override
    public Neuron_View get_Neuron_View() {
        return this.inlet_knot_view;
    }


    public void set_Signal(double signal) {
        this.signal = signal;
        this.inlet_knot_view.change_value(signal);
    }

    //**********************************************************************//

    @Override
    public void set_Layer(Neuron[] layer) {

    }

    @Override
    public void set_View(int x, int y, double radius) {
        this.inlet_knot_view = new Inlet_Knot_View(x,y,radius,this.signal);
    }

    @Override
    public Neuron[] getLayer() {
        return null;
    }

    public double get_Signal() {
        return this.signal;
    }

    @Override
    public double[] get_Weights() {
        return new double[0];
    }

    @Override
    public void set_Weights(double[] weights) { }




    @Override
    public double calculate_Error(int n) {
        return 0;
    }

    @Override
    public double get_Error() {
        return 0;
    }

    @Override
    public void set_Error(double error) {

    }

    @Override
    public void correct_Weights(double learning_Rate) {

    }




}
