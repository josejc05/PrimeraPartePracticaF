package ui;

import model.BacteriaPopulation;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class BacteriaPopulationDialog extends JDialog {
    private JTextField nameField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField initialBacteriaCountField;
    private JTextField temperatureField;
    private JTextField lightConditionField;
    private JTextField initialFoodField;
    private JTextField peakDayField;
    private JTextField peakFoodField;
    private JTextField finalFoodField;
    private int dialogResult = JOptionPane.CANCEL_OPTION;

    public BacteriaPopulationDialog() {
        setModal(true);
        setLayout(new GridLayout(11, 2));

        // Create fields to enter the details of the bacteria population
        nameField = new JTextField();
        startDateField = new JTextField();
        endDateField = new JTextField();
        initialBacteriaCountField = new JTextField();
        temperatureField = new JTextField();
        lightConditionField = new JTextField();
        initialFoodField = new JTextField();
        peakDayField = new JTextField();
        peakFoodField = new JTextField();
        finalFoodField = new JTextField();

        // Add fields to the dialog
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Start Date (YYYY-MM-DD):"));
        add(startDateField);
        add(new JLabel("End Date (YYYY-MM-DD):"));
        add(endDateField);
        add(new JLabel("Initial Bacteria Count:"));
        add(initialBacteriaCountField);
        add(new JLabel("Temperature:"));
        add(temperatureField);
        add(new JLabel("Light Condition:"));
        add(lightConditionField);
        add(new JLabel("Initial Food:"));
        add(initialFoodField);
        add(new JLabel("Peak Day:"));
        add(peakDayField);
        add(new JLabel("Peak Food:"));
        add(peakFoodField);
        add(new JLabel("Final Food:"));
        add(finalFoodField);

        // Add OK and Cancel buttons
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dialogResult = JOptionPane.OK_OPTION;
            setVisible(false);
        });
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dialogResult = JOptionPane.CANCEL_OPTION;
            setVisible(false);
        });
        add(cancelButton);

        pack();
    }

    public int showDialog() {
        setVisible(true);
        return dialogResult;
    }

    public BacteriaPopulation getBacteriaPopulation() {
        // Get the details from the fields and create a new BacteriaPopulation
        String name = nameField.getText();
        LocalDate startDate = LocalDate.parse(startDateField.getText());
        LocalDate endDate = LocalDate.parse(endDateField.getText());
        int initialBacteriaCount = Integer.parseInt(initialBacteriaCountField.getText());
        double temperature = Double.parseDouble(temperatureField.getText());
        String lightCondition = lightConditionField.getText();
        int initialFood = Integer.parseInt(initialFoodField.getText());
        int peakDay = Integer.parseInt(peakDayField.getText());
        int peakFood = Integer.parseInt(peakFoodField.getText());
        int finalFood = Integer.parseInt(finalFoodField.getText());

        return new BacteriaPopulation(name, startDate, endDate, initialBacteriaCount, temperature, lightCondition, initialFood, peakDay, peakFood, finalFood);
    }
}