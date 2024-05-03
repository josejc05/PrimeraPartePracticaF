package ui;

import model.Experiment;
import model.BacteriaPopulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExperimentPanel extends JPanel {
    private Experiment experiment;

    public ExperimentPanel() {
        setLayout(new BorderLayout());

        // Create a button to add a new bacteria population
        JButton addButton = new JButton("Add Bacteria Population");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement the logic to add a new bacteria population
            }
        });
        add(addButton, BorderLayout.NORTH);

        // Create a list to display the bacteria populations
        JList<BacteriaPopulation> list = new JList<>();
        add(new JScrollPane(list), BorderLayout.CENTER);

        // Create a button to remove a bacteria population
        JButton removeButton = new JButton("Remove Bacteria Population");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement the logic to remove a bacteria population
            }
        });
        add(removeButton, BorderLayout.SOUTH);
    }
}