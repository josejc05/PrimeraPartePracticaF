package ui;

import model.BacteriaPopulation;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;

public class BacteriaPopulationDialog extends JDialog {
    private JTextField nameField;
    private JTextField startDateField;
    private JTextField initialBacteriaCountField;
    private JTextField temperatureField;
    private JTextField lightConditionField;
    private JTextField foodDoseField;
    private JTextField durationField;
    private JTextField foodSupplyPatternField;
    private int dialogResult = JOptionPane.CANCEL_OPTION;

    public BacteriaPopulationDialog() {
        setModal(true);
        setLayout(new GridLayout(11, 2));

        nameField = new JTextField();
        startDateField = new JTextField();
        initialBacteriaCountField = new JTextField();
        temperatureField = new JTextField();
        lightConditionField = new JTextField();
        foodDoseField = new JTextField();
        durationField = new JTextField();
        foodSupplyPatternField = new JTextField();

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Start Date (YYYY-MM-DD):"));
        add(startDateField);
        add(new JLabel("Initial Bacteria Count:"));
        add(initialBacteriaCountField);
        add(new JLabel("Temperature:"));
        add(temperatureField);
        add(new JLabel("Light Condition:"));
        add(lightConditionField);
        add(new JLabel("Food Dose (micrograms):"));
        add(foodDoseField);
        add(new JLabel("Duration:"));
        add(durationField);
        add(new JLabel("Food Supply Pattern:"));
        add(foodSupplyPatternField);

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
        String name = nameField.getText();
        LocalDate startDate = LocalDate.parse(startDateField.getText());
        int initialBacteriaCount = Integer.parseInt(initialBacteriaCountField.getText());
        double temperature = Double.parseDouble(temperatureField.getText());
        String lightCondition = lightConditionField.getText();
        long[] foodDoseMicrograms = Arrays.stream(foodDoseField.getText().split(","))
                .mapToLong(Long::parseLong)
                .toArray();
        int duration = Integer.parseInt(durationField.getText());
        String foodSupplyPattern = foodSupplyPatternField.getText();

        return new BacteriaPopulation(name, startDate, initialBacteriaCount, temperature, lightCondition, foodDoseMicrograms, duration, foodSupplyPattern);
    }
}