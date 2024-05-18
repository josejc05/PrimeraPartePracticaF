package ui;

import ui.SimulationResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiment {
    private Cell[][] plate;
    private int days;
    private int foodPerDay;
    private static final Random RANDOM = new Random();

    public Experiment(int size, int days, int foodPerDay) {
        this.plate = new Cell[size][size];
        this.days = days;
        this.foodPerDay = foodPerDay;

        // Inicializar el plato con celdas y bacterias en el centro
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                plate[i][j] = new Cell();
            }
        }
        for (int i = size / 2 - 2; i < size / 2 + 2; i++) {
            for (int j = size / 2 - 2; j < size / 2 + 2; j++) {
                for (int k = 0; k < 100; k++) {
                    plate[i][j].addBacteria(new Bacteria());
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
                    cell.simulate();
                    List<Bacteria> movingBacteria = new ArrayList<>();
                    for (Bacteria bacteria : cell.getBacteriaList()) {
                        if (bacteria.shouldMove()) {
                            movingBacteria.add(bacteria);
                        }
                    }
                    cell.getBacteriaList().removeAll(movingBacteria);
                    for (Bacteria bacteria : movingBacteria) {
                        int[] dx = {-1, 0, 1, 0};
                        int[] dy = {0, 1, 0, -1};
                        int dir = RANDOM.nextInt(4);
                        int ni = i + dx[dir], nj = j + dy[dir];
                        if (ni >= 0 && ni < plate.length && nj >= 0 && nj < plate[ni].length) {
                            plate[ni][nj].addBacteria(bacteria);
                        } else {
                            cell.addBacteria(bacteria);
                        }
                    }
                    result.setBacteriaCount(day, i, j, cell.getBacteriaList().size());
                    result.setFoodAmount(day, i, j, cell.getFood());
                }
            }
        }

        return result;
    }

    public int getDays() {
        return days;
    }
}