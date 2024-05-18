package model;

import ui.SimulationResult;
import java.util.ArrayList;
import java.util.List;

public class Experiment {
    private List<BacteriaPopulation> bacteriaPopulations;
    private int days;
    private int foodPerDay;

    public Experiment(int size, int days, int foodPerDay) {
        this.bacteriaPopulations = new ArrayList<>();
        this.days = days;
        this.foodPerDay = foodPerDay;
    }

    public void addBacteriaPopulation(BacteriaPopulation bacteriaPopulation) {
        this.bacteriaPopulations.add(bacteriaPopulation);
    }

    public void removeBacteriaPopulation(BacteriaPopulation bacteriaPopulation) {
        this.bacteriaPopulations.remove(bacteriaPopulation);
    }

    public List<BacteriaPopulation> getBacteriaPopulations() {
        return bacteriaPopulations;
    }

    public int getDays() {
        return this.days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public SimulationResult run() {
        SimulationResult result = new SimulationResult(days, bacteriaPopulations.size());
        return result;
    }
}