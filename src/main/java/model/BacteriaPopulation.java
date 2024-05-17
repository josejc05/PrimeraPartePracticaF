// En BacteriaPopulation.java
package model;

import java.time.LocalDate;

public class BacteriaPopulation {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int initialBacteriaCount;
    private double temperature;
    private String lightCondition;
    private long[] foodDoseMicrograms;
    private int duration;
    private String foodSupplyPattern;

    public BacteriaPopulation(String name, LocalDate startDate, LocalDate endDate, int initialBacteriaCount, double temperature, String lightCondition, long[] foodDoseMicrograms, int duration, String foodSupplyPattern) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialBacteriaCount = initialBacteriaCount;
        this.temperature = temperature;
        this.lightCondition = lightCondition;
        this.foodDoseMicrograms = foodDoseMicrograms;
        this.duration = duration;
        this.foodSupplyPattern = foodSupplyPattern;
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

    public long[] getFoodDoseMicrograms() {
        return foodDoseMicrograms;
    }

    public int getDuration() {
        return duration;
    }

    public String getFoodSupplyPattern() {
        return foodSupplyPattern;
    }
}