package ui;

import java.util.ArrayList;
import java.util.List;

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
}
