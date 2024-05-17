package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiment {
    private Cell[][] plate;
    private int days;
    private int foodPerDay;

    public Experiment(int size, int days, int foodPerDay) {
        this.plate = new Cell[size][size];
        this.days = days;
        this.foodPerDay = foodPerDay;

        // Inicializar el plato con celdas y bacterias en el centro
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                plate[i][j] = new Cell();
                if (i >= 8 && i < 12 && j >= 8 && j < 12) {
                    for (int k = 0; k < 100; k++) {
                        plate[i][j].addBacteria(new Bacteria());
                    }
                }
            }
        }
    }

    public SimulationResult run() {
        SimulationResult result = new SimulationResult(days, plate.length);

        for (int day = 0; day < days; day++) {
            // Distribuir comida
            for (Cell[] row : plate) {
                for (Cell cell : row) {
                    cell.addFood(foodPerDay / (plate.length * plate.length));
                }
            }

            // Simular cada bacteria
            for (int i = 0; i < plate.length; i++) {
                for (int j = 0; j < plate[i].length; j++) {
                    Cell cell = plate[i][j];
                    List<Bacteria> newBacteria = new ArrayList<>();

                    for (Bacteria bacteria : cell.getBacteriaList()) {
                        int foodInCell = cell.getFood();
                        if (foodInCell >= 100) {
                            bacteria.eat(20);
                            cell.removeFood(20);
                        } else if (foodInCell > 9) {
                            bacteria.eat(10);
                            cell.removeFood(10);
                        }

                        if (bacteria.isDead(foodInCell)) {
                            continue;
                        }

                        if (bacteria.shouldMove()) {
                            int[] dx = {-1, 0, 1, 0};
                            int[] dy = {0, 1, 0, -1};
                            int dir = new Random().nextInt(4);
                            int ni = i + dx[dir], nj = j + dy[dir];
                            if (ni >= 0 && ni < plate.length && nj >= 0 && nj < plate[ni].length) {
                                newBacteria.add(bacteria);
                            }
                        }

                        for (int k = 0; k < bacteria.getOffspringCount(); k++) {
                            newBacteria.add(new Bacteria());
                        }
                    }

                    cell.getBacteriaList().clear();
                    cell.getBacteriaList().addAll(newBacteria);
                    result.setBacteriaCount(day, i, j, cell.getBacteriaList().size());
                    result.setFoodAmount(day, i, j, cell.getFood());
                }
            }
        }

        return result;
    }
}