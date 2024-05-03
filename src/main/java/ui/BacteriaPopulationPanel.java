package ui;

import model.BacteriaPopulation;

import javax.swing.*;
import java.awt.*;

public class BacteriaPopulationPanel extends JPanel {
    private BacteriaPopulation bacteriaPopulation;

    public BacteriaPopulationPanel(BacteriaPopulation bacteriaPopulation) {
        this.bacteriaPopulation = bacteriaPopulation;
        setLayout(new BorderLayout());

        // Display the details of the bacteria population
        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setText(bacteriaPopulation.toString());
        add(detailsTextArea, BorderLayout.CENTER);
    }
}