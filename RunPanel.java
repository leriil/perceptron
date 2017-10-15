package com.snt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RunPanel {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
        Dimension dimension = new Dimension(90, 50);
        Perceptron perc = new Perceptron();



        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Dimension preferredSize = new Dimension(100, 500);
        leftPanel.setPreferredSize(preferredSize);
        leftPanel.setMinimumSize(preferredSize);
        leftPanel.setMaximumSize(preferredSize);

        JButton top1 = new JButton("guess");
        top1.setPreferredSize(dimension);
        leftPanel.add(top1);


        JButton top2 = new JButton("teach");
        top2.setPreferredSize(dimension);
        leftPanel.add(top2);
        JButton top3 = new JButton("clear");
        top3.setPreferredSize(dimension);
        leftPanel.add(top3);

        Panel rightPanel = new Panel();

        wrapper.add(leftPanel);
        wrapper.add(rightPanel);


        top1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.save();
                rightPanel.breakPictureIntoArrays();

              perc.start();

            }
        });

        top2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perc.teach();
            }
        });

        top3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.clear();
            }
        });
        frame.add(wrapper);
        frame.setVisible(true);
    }
}
