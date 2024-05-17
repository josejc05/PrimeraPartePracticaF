// En ExperimentPanel.java
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
import java.util.Arrays;
import java.util.Vector;

public class ExperimentPanel extends JPanel {
    private Experiment experiment;
    private JList<BacteriaPopulation> list;
    private String currentFile;

    public ExperimentPanel() {
        setLayout(new BorderLayout());
        experiment = new Experiment();
        list = new JList<>();

        // Create a button to open an existing experiment
        JButton openButton = new JButton("Open Experiment");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileChooser.getSelectedFile().getPath();
                    try {
                        experiment = ExperimentFileHandler.loadExperiment(currentFile);
                        updateList();
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Failed to open experiment: " + ex.getMessage());
                    }
                }
            }
        });
        add(openButton, BorderLayout.WEST);

        // Create a button to save the current experiment
        JButton saveButton = new JButton("Save Experiment");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFile != null) {
                    try {
                        ExperimentFileHandler.saveExperiment(experiment, currentFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Failed to save experiment: " + ex.getMessage());
                    }
                } else {
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        currentFile = fileChooser.getSelectedFile().getPath();
                        try {
                            ExperimentFileHandler.saveExperiment(experiment, currentFile);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Failed to save experiment: " + ex.getMessage());
                        }
                    }
                }
            }
        });
        add(saveButton, BorderLayout.WEST);

        // Create a button to add a new bacteria population
        JButton addButton = new JButton("Add Bacteria Population");
        addButton.setBackground(Color.GREEN); // Set button color to green
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
        removeButton.setBackground(Color.RED); // Set button color to red
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
        viewDetailsButton.setBackground(Color.BLUE); // Set button color to blue
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the details of the selected bacteria population
                BacteriaPopulation selectedPopulation = list.getSelectedValue();
                if (selectedPopulation != null) {
                    String details = "Name: " + selectedPopulation.getName() + "\n"
                            + "Start Date: " + selectedPopulation.getStartDate() + "\n"
                            + "End Date: " + selectedPopulation.getEndDate() + "\n"
                            + "Initial Bacteria Count: " + selectedPopulation.getInitialBacteriaCount() + "\n"
                            + "Temperature: " + selectedPopulation.getTemperature() + "\n"
                            + "Light Condition: " + selectedPopulation.getLightCondition() + "\n"
                            + "Food Dose (micrograms): " + Arrays.toString(selectedPopulation.getFoodDoseMicrograms()) + "\n"
                            + "Duration: " + selectedPopulation.getDuration() + "\n"
                            + "Food Supply Pattern: " + selectedPopulation.getFoodSupplyPattern();
                    JOptionPane.showMessageDialog(null, details, "Bacteria Population Details", JOptionPane.INFORMATION_MESSAGE);
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