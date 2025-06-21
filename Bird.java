import java.time.LocalDateTime;
import java.util.Random;
import java.io.*;

class Bird extends Pet {
    public boolean canFly;

    public Bird(String name, int age, boolean canFly, String favoriteFood) {
        super(name, age, favoriteFood);
        this.canFly = canFly;
    }

    public void feed(String birdFavFood) {
        this.hunger = 0;
        this.lastFedTime = LocalDateTime.now();
        if (birdFavFood.equalsIgnoreCase(favoriteFood)) {
            this.happiness += 4;
            System.out.println(name + " Favourite food! Extra happiness gained.");
        } else {
            this.happiness += 2;
        }
        System.out.println(name + " has been fed.");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " chirps: Tweet! Tweet!");
    }

    public void fly() {
        if (canPerformAction()) {
            Random random = new Random();
            int chance = random.nextInt(100) + 1;
            if (canFly) {
                System.out.println(name + " is flying high in the sky!");
                if (chance <= 80) {
                    this.happiness += 2;
                    System.out.println(name + " is enjoying the flight!");
                } else {
                    this.hunger += 2;
                    System.out.println(name + " is getting tired and hungry from flying.");
                }
            } else {
                System.out.println(name + " can't fly, but it's enjoying the view from the ground.");
                this.happiness -= 1;
            }
            lastPlayedOrActionTime = LocalDateTime.now();
        } else {
            System.out.println(name + " is not ready to fly yet.");
        }
    }

    public void singMelody() {
        updateMood();
        if (canPerformAction()) {
            System.out.println(name + " starts singing a beautiful melody!");
            this.happiness += 2;
            if (mood.equals("Happy")) {
                System.out.println("The song is particularly enchanting today!");
                this.happiness += 1;
            }
            lastPlayedOrActionTime = LocalDateTime.now();
        } else {
            System.out.println(name + " is not in the mood to sing right now.");
        }
    }

    public void bathInWater() {
        if (canPerformAction()) {
            System.out.println(name + " splashes around in the water bath!");
            this.happiness += 3;
            this.hunger += 1;
            lastPlayedOrActionTime = LocalDateTime.now();
            if (canFly) {
                System.out.println(name + " shakes its feathers dry after the bath.");
            } else {
                System.out.println(name + " enjoys the water but needs help drying off.");
            }
        } else {
            System.out.println(name + " doesn't want to take a bath right now.");
        }
    }

    public boolean letOutOfCage() {
        updateMood();
        System.out.println("You open the cage door for " + name + "...");
        if (!mood.equals("Happy")) {
            if (canFly) {
                System.out.println(name + " sees an opportunity and flies away to freedom!");
                return true; // Indicates the bird should be removed from the list
            } else {
                System.out.println(name + " looks longingly at the open door but can't fly away.");
                this.happiness -= 2;
            }
        } else {
            System.out.println(name + " is happy and content, choosing to stay despite the open cage.");
            this.happiness += 2;
        }
        return false; // Bird stays
    }
}