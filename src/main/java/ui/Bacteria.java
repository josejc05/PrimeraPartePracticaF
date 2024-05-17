package ui;

import java.util.Random;

// En Bacteria.java
public class Bacteria {
    private static final Random RANDOM = new Random();
    private int foodEaten;

    public void eat(int food) {
        foodEaten += food;
    }

    public int getFoodEaten() {
        return foodEaten;
    }

    public int simulate(int foodInCell) {
        int foodEatenToday = 0;
        for (int i = 0; i < 10; i++) {
            int randomNumber = RANDOM.nextInt(100);
            if (foodInCell >= 100) {
                eat(20);
                foodInCell -= 20;
                foodEatenToday += 20;
                if (randomNumber < 3) {
                    return foodEatenToday;
                }
            } else if (foodInCell > 9) {
                eat(10);
                foodInCell -= 10;
                foodEatenToday += 10;
                if (randomNumber < 6) {
                    return foodEatenToday;
                }
            } else {
                if (randomNumber < 20) {
                    return foodEatenToday;
                }
            }
        }
        return foodEatenToday;
    }
}