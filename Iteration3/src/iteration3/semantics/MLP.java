/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteration3.semantics;

import java.util.ArrayList;

/**
 *
 * @author Hebron
 */
public class MLP {
    private ArrayList<Perceptron> inputLayer;
    private ArrayList<Perceptron> hiddenLayer;
    private ArrayList<Perceptron> outputLayer;
    private double learningRate;

    public MLP(double learningRate) {
        this.inputLayer = new ArrayList<>(850);
        int hidden = (int)(inputLayer.size() * 1.5);
        this.hiddenLayer = new ArrayList<>(hidden);
        this.outputLayer = new ArrayList<>(0);
        this.learningRate = learningRate;
    }
}
