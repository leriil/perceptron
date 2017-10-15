package com.snt;

import static com.snt.Panel.pixs;

/**
 * Created by Leriil on 05.10.2017.
 */
public class Perceptron {

    public double[] weights = new double[100];
    int[] xs = new int[2500];
    int[] Xs = new int[100];
    int y;
    int delta;


    Perceptron() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (-0.5) + (Math.random() * 0.5);
        }
    }

    public void start() {
        for (int j = 0; j < 100; j++) {
            int[] xs = pixs[j];
            if (checkIfEmpty(xs)) Xs[j] = 0;
            else Xs[j] = 1;
        }
        if (calculateY() == 1) System.out.println("It's a star");
        else System.out.println("It's a moon");

    }

    public boolean checkIfEmpty(int[] xes) {
        int count = 0;
        boolean isEmpty = true;
        for (int i = 0; i < xes.length; i++) {
            if (xes[i] != -1) {
                count++;
            }
        }
        if (count > 5) isEmpty = false;
        return isEmpty;
    }

    public int calculateY() {
        int sum = 0;
        for (int i = 0; i < Xs.length; i++) {
            sum += weights[i] * Xs[i];
        }
        if (sum >= 0) y = 1;
        else y = 0;
        return y;
    }

    public void teach() {
        int yk = 0;
        if (y == 0) yk = 1;
        delta = yk - y;
        changeWeights();
        System.out.println("weights have been changed");
    }

    public void changeWeights() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = weights[i] + delta * (double)Xs[i] / 2;
        }
    }
}
