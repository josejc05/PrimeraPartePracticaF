package model;

import java.util.ArrayList;
import java.util.List;

public class Experiment {
    private List<BacteriaPopulation> bacteriaPopulations;

    public Experiment() {
        this.bacteriaPopulations = new ArrayList<>();
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
}