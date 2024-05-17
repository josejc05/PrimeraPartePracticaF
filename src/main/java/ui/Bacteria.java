package ui;

import java.util.Random;

public class Bacteria {
    private static final Random RANDOM = new Random();
    private int foodEaten;

    public void eat(int food) {
        foodEaten += food;
    }

    public int getFoodEaten() {
        return foodEaten;
    }

    public boolean isDead(int foodInCell) {
        int randomNumber = RANDOM.nextInt(100);
        if (foodInCell >= 100) {
            return randomNumber < 3;
        } else if (foodInCell > 9) {
            return randomNumber < 6;
        } else {
            return randomNumber < 20;
        }
    }

    public boolean shouldMove() {
        int randomNumber = RANDOM.nextInt(100);
        return randomNumber >= 60;
    }

    public int getOffspringCount() {
        if (foodEaten >= 150) {
            return 3;
        } else if (foodEaten >= 100) {
            return 2;
        } else if (foodEaten >= 50) {
            return 1;
        } else {
            return 0;
        }
    }
}