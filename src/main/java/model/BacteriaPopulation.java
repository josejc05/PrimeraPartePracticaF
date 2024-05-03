package model;

import java.time.LocalDate;

public class BacteriaPopulation {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int initialBacteriaCount;
    private double temperature;
    private String lightCondition;
    private int[] foodDose;

    public BacteriaPopulation(String name, LocalDate startDate, LocalDate endDate, int initialBacteriaCount, double temperature, String lightCondition, int initialFood, int peakDay, int peakFood, int finalFood) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialBacteriaCount = initialBacteriaCount;
        this.temperature = temperature;
        this.lightCondition = lightCondition;
        this.foodDose = new int[30];
        calculateFoodDose(initialFood, peakDay, peakFood, finalFood);
    }

    private void calculateFoodDose(int initialFood, int peakDay, int peakFood, int finalFood) {
        // Calculate the increment and decrement rates
        double incrementRate = (double) (peakFood - initialFood) / peakDay;
        double decrementRate = (double) (peakFood - finalFood) / (30 - peakDay);

        // Calculate the food dose for each day
        for (int i = 0; i < 30; i++) {
            if (i < peakDay) {
                foodDose[i] = initialFood + (int) (i * incrementRate);
            } else {
                foodDose[i] = peakFood + (int) ((i - peakDay) * decrementRate);
            }
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getInitialBacteriaCount() {
        return initialBacteriaCount;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getLightCondition() {
        return lightCondition;
    }

    public int[] getFoodDose() {
        return foodDose;
    }
}