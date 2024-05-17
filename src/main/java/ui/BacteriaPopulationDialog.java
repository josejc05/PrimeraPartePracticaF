// En BacteriaPopulationDialog.java
package ui;

import model.BacteriaPopulation;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;

public class BacteriaPopulationDialog extends JDialog {
    private JTextField nameField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField initialBacteriaCountField;
    private JTextField temperatureField;
    private JTextField lightConditionField;
    private JTextField foodDoseField;
    private JTextField durationField; // Field for experiment duration
    private JTextField foodSupplyPatternField; // Field for food supply pattern
    private int dialogResult = JOptionPane.CANCEL_OPTION;

    public BacteriaPopulationDialog() {
        setModal(true);
        setLayout(new GridLayout(13, 2));

        // Create fields to enter the details of the bacteria population
        nameField = new JTextField();
        startDateField = new JTextField();
        endDateField = new JTextField();
        initialBacteriaCountField = new JTextField();
        temperatureField = new JTextField();
        lightConditionField = new JTextField();
        foodDoseField = new JTextField();
        durationField = new JTextField(); // Initialize the new field
        foodSupplyPatternField = new JTextField(); // Initialize the new field

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
        add(new JLabel("Food Dose (micrograms):"));
        add(foodDoseField);
        add(new JLabel("Duration:")); // Add label for the new field
        add(durationField); // Add the new field
        add(new JLabel("Food Supply Pattern:")); // Add label for the new field
        add(foodSupplyPatternField); // Add the new field

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
        long[] foodDoseMicrograms = Arrays.stream(foodDoseField.getText().split(","))
                .mapToLong(Long::parseLong)
                .toArray(); // Convertir a long
        int duration = Integer.parseInt(durationField.getText());
        String foodSupplyPattern = foodSupplyPatternField.getText();

        return new BacteriaPopulation(name, startDate, endDate, initialBacteriaCount, temperature, lightCondition, foodDoseMicrograms, duration, foodSupplyPattern);
    }
}