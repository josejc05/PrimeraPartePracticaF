// En Cell.java
package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {
    private List<Bacteria> bacteriaList = new ArrayList<>();
    private int food;

    public void addFood(int food) {
        this.food += food;
    }

    public void addBacteria(Bacteria bacteria) {
        bacteriaList.add(bacteria);
    }

    public List<Bacteria> getBacteriaList() {
        return bacteriaList;
    }

    public int getFood() {
        return food;
    }

    public void removeFood(int food) {
        this.food -= food;
    }

    public void simulate() {
        List<Bacteria> newBacteriaList = new ArrayList<>();
        for (Bacteria bacteria : bacteriaList) {
            int foodEaten = bacteria.simulate(food);
            food -= foodEaten;
            if (!bacteria.isDead(food)) {
                if (bacteria.shouldMove()) {
                    // La lógica de movimiento se manejará en la clase Experiment
                } else {
                    newBacteriaList.add(bacteria);
                }
                for (int i = 0; i < bacteria.getOffspringCount(); i++) {
                    newBacteriaList.add(new Bacteria());
                }
            }
        }
        bacteriaList.clear();
        bacteriaList.addAll(newBacteriaList);
    }
}