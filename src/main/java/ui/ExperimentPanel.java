package ui;

import model.Experiment;
import model.BacteriaPopulation;
import data.ExperimentFileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;

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
                // Open a dialog to get the details of the new bacteria population
                BacteriaPopulationDialog dialog = new BacteriaPopulationDialog();
                if (dialog.showDialog() == JOptionPane.OK_OPTION) {
                    BacteriaPopulation newPopulation = dialog.getBacteriaPopulation();
                    experiment.addBacteriaPopulation(newPopulation);
                    updateList();
                }
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
                // Remove the selected bacteria population
                BacteriaPopulation selectedPopulation = list.getSelectedValue();
                if (selectedPopulation != null) {
                    experiment.removeBacteriaPopulation(selectedPopulation);
                    updateList();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a bacteria population to remove.");
                }
            }
        });
        add(removeButton, BorderLayout.SOUTH);

        // Create a button to view details of a bacteria population
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the details of the selected bacteria population
                BacteriaPopulation selectedPopulation = list.getSelectedValue();
                if (selectedPopulation != null) {
                    BacteriaPopulationPanel detailsPanel = new BacteriaPopulationPanel(selectedPopulation);
                    JOptionPane.showMessageDialog(null, detailsPanel, "Bacteria Population Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a bacteria population to view details.");
                }
            }
        });
        add(viewDetailsButton, BorderLayout.EAST);
    }

    private void updateList() {
        // Update the list of bacteria populations
        list.setListData(new Vector<BacteriaPopulation>(experiment.getBacteriaPopulations()));
    }
}