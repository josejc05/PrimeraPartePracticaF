// En Bacteria.java
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
        return RANDOM.nextInt(100) >= 60;
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

    public int simulate(int foodInCell) {
        int foodEatenToday = 0;
        for (int i = 0; i < 10; i++) {
            if (foodInCell >= 100) {
                eat(20);
                foodInCell -= 20;
                foodEatenToday += 20;
            } else if (foodInCell > 9) {
                eat(10);
                foodInCell -= 10;
                foodEatenToday += 10;
            }
        }
        return foodEatenToday;
    }
}