package ui;

import model.Experiment;
import model.BacteriaPopulation;
import data.ExperimentFileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ExperimentPanel extends JPanel {
    private Experiment experiment;
    private JList<BacteriaPopulation> list;

    public ExperimentPanel() {
        setLayout(new BorderLayout());
        experiment = new Experiment();
        list = new JList<>();

        // Create a button to add a new bacteria population
        JButton addButton = new JButton("Add Bacteria Population");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement the logic to add a new bacteria population
                // For now, we just add a dummy population
                experiment.addBacteriaPopulation(new BacteriaPopulation("Dummy", LocalDate.now(), LocalDate.now().plusDays(30), 100, 37.0, "Alta", 10, 15, 20, 5));
                updateList();
            }
        });
        add(addButton, BorderLayout.NORTH);

        // Create a list to display the bacteria populations
        updateList();
        add(new JScrollPane(list), BorderLayout.CENTER);

        // Create a button to remove a bacteria population
        JButton removeButton = new JButton("Remove Bacteria Population");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement the logic to remove a bacteria population
                // For now, we just remove the selected population
                BacteriaPopulation selectedPopulation = list.getSelectedValue();
                if (selectedPopulation != null) {
                    experiment.removeBacteriaPopulation(selectedPopulation);
                    updateList();
                }
            }
        });
        add(removeButton, BorderLayout.SOUTH);
    }

    private void updateList() {
        // Update the list of bacteria populations
        list.setListData(new Vector<BacteriaPopulation>(experiment.getBacteriaPopulations()));
    }
}