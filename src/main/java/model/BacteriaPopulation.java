package model;

import java.time.LocalDate;

public class BacteriaPopulation {
    private String name;
    private LocalDate startDate;
    private int initialBacteriaCount;
    private double temperature;
    private String lightCondition;
    private long[] foodDoseMicrograms;
    private int duration;
    private String foodSupplyPattern;

    public BacteriaPopulation(String name, LocalDate startDate, int initialBacteriaCount, double temperature, String lightCondition, long[] foodDoseMicrograms, int duration, String foodSupplyPattern) {
        this.name = name;
        this.startDate = startDate;
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