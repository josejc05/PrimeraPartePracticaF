package model;

import java.time.LocalDate;

// En BacteriaPopulation.java
public class BacteriaPopulation {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int initialBacteriaCount;
    private double temperature;
    private String lightCondition;
    private long[] foodDoseMicrograms; // Cambiado a microgramos
    private int duration; // Duración del experimento ahora es variable
    private String foodSupplyPattern; // Nuevo campo para el patrón de suministro de alimentos

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

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setInitialBacteriaCount(int initialBacteriaCount) {
        this.initialBacteriaCount = initialBacteriaCount;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setLightCondition(String lightCondition) {
        this.lightCondition = lightCondition;
    }

    public void setFoodDoseMicrograms(long[] foodDoseMicrograms) {
        this.foodDoseMicrograms = foodDoseMicrograms;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFoodSupplyPattern(String foodSupplyPattern) {
        this.foodSupplyPattern = foodSupplyPattern;
    }
}