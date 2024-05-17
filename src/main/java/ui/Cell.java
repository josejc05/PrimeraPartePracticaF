package ui;

import java.util.ArrayList;
import java.util.List;

// En Cell.java
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
            if (foodEaten >= 150) {
                newBacteriaList.add(new Bacteria());
                newBacteriaList.add(new Bacteria());
                newBacteriaList.add(new Bacteria());
            } else if (foodEaten >= 100) {
                newBacteriaList.add(new Bacteria());
                newBacteriaList.add(new Bacteria());
            } else if (foodEaten >= 50) {
                newBacteriaList.add(new Bacteria());
            }
        }
        bacteriaList.addAll(newBacteriaList);
    }
}