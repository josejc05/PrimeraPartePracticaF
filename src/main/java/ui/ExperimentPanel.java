// En ExperimentPanel.java
package ui;

import model.Experiment;
import model.BacteriaPopulation;
import data.ExperimentFileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

public class ExperimentPanel extends JPanel {
    private Experiment experiment;
    private JList<BacteriaPopulation> list;
    private String currentFile;

    public ExperimentPanel() {
        setLayout(new BorderLayout());
        experiment = new Experiment();
        list = new JList<>();

        JButton openButton = new JButton("Open Experiment");
        openButton.addActionListener(e -> {
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
        });
        add(openButton, BorderLayout.WEST);

        JButton saveButton = new JButton("Save Experiment");
        saveButton.addActionListener(e -> {
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
        });
        add(saveButton, BorderLayout.WEST);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add Bacteria Population");
        addButton.setBackground(Color.GREEN);
        addButton.addActionListener(e -> {
            BacteriaPopulationDialog dialog = new BacteriaPopulationDialog();
            if (dialog.showDialog() == JOptionPane.OK_OPTION) {
                BacteriaPopulation newPopulation = dialog.getBacteriaPopulation();
                experiment.addBacteriaPopulation(newPopulation);
                updateList();
            }
        });
        northPanel.add(addButton);

        JPanel sortButtonsPanel = new JPanel();
        JButton sortByStartDateButton = new JButton("Sort by Start Date");
        sortByStartDateButton.addActionListener(e -> {
            experiment.getBacteriaPopulations().sort((p1, p2) -> p1.getStartDate().compareTo(p2.getStartDate()));
            updateList();
        });
        sortButtonsPanel.add(sortByStartDateButton);

        JButton sortByNameButton = new JButton("Sort by Name");
        sortByNameButton.addActionListener(e -> {
            experiment.getBacteriaPopulations().sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
            updateList();
        });
        sortButtonsPanel.add(sortByNameButton);

        JButton sortByBacteriaCountButton = new JButton("Sort by Bacteria Count");
        sortByBacteriaCountButton.addActionListener(e -> {
            experiment.getBacteriaPopulations().sort((p1, p2) -> Integer.compare(p1.getInitialBacteriaCount(), p2.getInitialBacteriaCount()));
            updateList();
        });
        sortButtonsPanel.add(sortByBacteriaCountButton);

        northPanel.add(sortButtonsPanel);
        add(northPanel, BorderLayout.NORTH);

        updateList();
        add(new JScrollPane(list), BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove Bacteria Population");
        removeButton.setBackground(Color.RED);
        removeButton.addActionListener(e -> {
            BacteriaPopulation selectedPopulation = list.getSelectedValue();
            if (selectedPopulation != null) {
                experiment.removeBacteriaPopulation(selectedPopulation);
                updateList();
            } else {
                JOptionPane.showMessageDialog(null, "Please select a bacteria population to remove.");
            }
        });
        add(removeButton, BorderLayout.SOUTH);

        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBackground(Color.BLUE);
        viewDetailsButton.addActionListener(e -> {
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
        });
        add(viewDetailsButton, BorderLayout.EAST);
    }

    private void updateList() {
        list.setListData(new Vector<BacteriaPopulation>(experiment.getBacteriaPopulations()));
        list.repaint();
    }
}