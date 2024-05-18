package ui;

import model.Experiment;
import model.BacteriaPopulation;
import data.ExperimentFileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class ExperimentPanel extends JPanel {
    private Experiment experiment;
    private JList<BacteriaPopulation> list;
    private String currentFile;

    public ExperimentPanel() {
        setLayout(new BorderLayout());
        experiment = new Experiment(20, 10, 40000);
        list = new JList<>();

        JButton openButton = new JButton("Open Experiment");
        openButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    experiment = ExperimentFileHandler.loadExperiment(currentFile);
                    updateList();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
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
                    ex.printStackTrace();
                }
            }
        });
        add(saveButton, BorderLayout.EAST);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add Bacteria Population");
        addButton.setBackground(Color.GREEN);
        addButton.addActionListener(e -> {
            BacteriaPopulationDialog dialog = new BacteriaPopulationDialog();
            if (dialog.showDialog() == JOptionPane.OK_OPTION) {
                BacteriaPopulation bacteriaPopulation = dialog.getBacteriaPopulation();
                experiment.addBacteriaPopulation(bacteriaPopulation);
                updateList();
            }
        });
        northPanel.add(addButton);

        JPanel sortButtonsPanel = new JPanel();
        JButton sortByStartDateButton = new JButton("Sort by Start Date");
        sortByStartDateButton.addActionListener(e -> {
            List<BacteriaPopulation> sortedBacteriaPopulations = new ArrayList<>(experiment.getBacteriaPopulations());
            sortedBacteriaPopulations.sort(Comparator.comparing(BacteriaPopulation::getStartDate));
            showSortedBacteriaPopulations(sortedBacteriaPopulations);
        });
        sortButtonsPanel.add(sortByStartDateButton);

        JButton sortByNameButton = new JButton("Sort by Name");
        sortByNameButton.addActionListener(e -> {
            List<BacteriaPopulation> sortedBacteriaPopulations = new ArrayList<>(experiment.getBacteriaPopulations());
            sortedBacteriaPopulations.sort(Comparator.comparing(BacteriaPopulation::getName));
            showSortedBacteriaPopulations(sortedBacteriaPopulations);
        });
        sortButtonsPanel.add(sortByNameButton);

        JButton sortByBacteriaCountButton = new JButton("Sort by Bacteria Count");
        sortByBacteriaCountButton.addActionListener(e -> {
            List<BacteriaPopulation> sortedBacteriaPopulations = new ArrayList<>(experiment.getBacteriaPopulations());
            sortedBacteriaPopulations.sort(Comparator.comparingInt(BacteriaPopulation::getInitialBacteriaCount));
            showSortedBacteriaPopulations(sortedBacteriaPopulations);
        });
        sortButtonsPanel.add(sortByBacteriaCountButton);

        northPanel.add(sortButtonsPanel);
        add(northPanel, BorderLayout.NORTH);

        updateList();
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        JButton runButton = new JButton("Run Experiment");
        runButton.addActionListener(e -> {
            SimulationResult result = experiment.run();
            showSimulationResults(result);
        });
        southPanel.add(runButton);

        JButton removeButton = new JButton("Remove Bacteria Population");
        removeButton.setBackground(Color.RED);
        removeButton.addActionListener(e -> {
            BacteriaPopulation selectedPopulation = list.getSelectedValue();
            if (selectedPopulation != null) {
                experiment.removeBacteriaPopulation(selectedPopulation);
                updateList();
            }
        });
        southPanel.add(removeButton);

        add(southPanel, BorderLayout.SOUTH);

        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBackground(Color.BLUE);
        viewDetailsButton.addActionListener(e -> {
            BacteriaPopulation selectedPopulation = list.getSelectedValue();
            if (selectedPopulation != null) {
                BacteriaPopulationPanel bacteriaPopulationPanel = new BacteriaPopulationPanel(selectedPopulation);
                JFrame frame = new JFrame("Bacteria Population Details");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(bacteriaPopulationPanel);
                frame.pack();
                frame.setVisible(true);
            }
        });
        add(viewDetailsButton, BorderLayout.EAST);
    }

    private void updateList() {
        list.setListData(new Vector<BacteriaPopulation>(experiment.getBacteriaPopulations()));
        list.repaint();
    }

    private void showSortedBacteriaPopulations(List<BacteriaPopulation> sortedBacteriaPopulations) {
        JFrame frame = new JFrame("Sorted Bacteria Populations");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (BacteriaPopulation bacteriaPopulation : sortedBacteriaPopulations) {
            textArea.append(bacteriaPopulation.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public void showSimulationResults(SimulationResult result) {
        int size = result.getBacteriaCounts()[0].length;
        JPanel resultPanel = new JPanel(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel cellLabel = new JLabel();
                cellLabel.setOpaque(true);
                int bacteriaCount = result.getBacteriaCounts()[experiment.getDays() - 1][i][j];
                if (bacteriaCount >= 20) {
                    cellLabel.setBackground(Color.RED);
                } else if (bacteriaCount >= 15) {
                    cellLabel.setBackground(Color.MAGENTA);
                } else if (bacteriaCount >= 10) {
                    cellLabel.setBackground(Color.ORANGE);
                } else if (bacteriaCount >= 5) {
                    cellLabel.setBackground(Color.YELLOW);
                } else if (bacteriaCount >= 1) {
                    cellLabel.setBackground(Color.GREEN);
                } else {
                    cellLabel.setBackground(Color.WHITE);
                }
                resultPanel.add(cellLabel);
            }
        }

        JFrame resultFrame = new JFrame("Simulation Results");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.add(new JScrollPane(resultPanel));
        resultFrame.pack();
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);
    }

    public void runSimulation() {
        SimulationResult result = experiment.run();
        showSimulationResults(result);
    }
}