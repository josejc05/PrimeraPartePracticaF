package ui;

public class SimulationResult {
    private int[][][] bacteriaCounts;
    private int[][][] foodAmounts;

    public SimulationResult(int days, int size) {
        this.bacteriaCounts = new int[days][size][size];
        this.foodAmounts = new int[days][size][size];
    }

    public void setBacteriaCount(int day, int x, int y, int count) {
        bacteriaCounts[day][x][y] = count;
    }

    public void setFoodAmount(int day, int x, int y, int amount) {
        foodAmounts[day][x][y] = amount;
    }
}