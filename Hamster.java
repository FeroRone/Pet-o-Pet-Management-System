import java.time.LocalDateTime;
import java.io.*;

class Hamster extends Pet {
    public int cageSize;

    public Hamster(String name, int age, int cageSize, String favouriteFood) {
        super(name, age, favouriteFood);
        this.cageSize = cageSize;
    }

    public void feed(String HamsterFavFood) {
        this.hunger = 0;
        this.lastFedTime = LocalDateTime.now();
        
        if (HamsterFavFood.equalsIgnoreCase(favoriteFood)) {
            this.happiness += 4; // Increase happiness more when fed favorite food
            System.out.println(name + " Favourite food! Extra happiness gained.");
        } else {
            this.happiness += 2; // Default happiness increase
        }
        System.out.println(name + " has been fed.");
    }
    
    public void rolling() {
        if (canPerformAction()) {
            this.happiness += 2;
            this.hunger += 1;
            System.out.println(name + " the hamster enjoy rolling! Happiness increased, but became a little hungry.");
            lastPlayedOrActionTime = LocalDateTime.now(); // Update the last action time
        } else {
            System.out.println(name + " is not ready to roll yet.");
        }
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " squeaks: Squeak! Squeak!");
    }

    public void burrow() {
        updateMood();
        if (mood.equals("Sad")) {
            System.out.println(name + " doesn't feel like burrowing. Try cheering them up first!");
            return;
        }
        if (canPerformAction()) {
            System.out.println(name + " burrows into their bedding, creating a cozy nest!");
            this.happiness += 2;
            this.hunger += 1;
            lastPlayedOrActionTime = LocalDateTime.now();
            if (mood.equals("Happy")) {
                System.out.println(name + " seems extra comfortable in their new nest!");
                this.happiness += 1;
            }
        } else {
            System.out.println(name + " is too tired to burrow right now.");
        }
    }

    public void exploreObstaclesCourse() {
        updateMood();
        if (mood.equals("Sad")) {
            System.out.println(name + " isn't interested in exploring. Maybe try something else to improve their mood?");
            return;
        }
        if (canPerformAction()) {
            double successChance = Math.random();
            if (successChance > 0.3) {
                System.out.println(name + " successfully navigates the obstacle course!");
                this.happiness += 3;
            } else {
                System.out.println(name + " gets a bit confused by the obstacle course but still has fun.");
                this.happiness += 1;
            }
            this.hunger += 2;
            lastPlayedOrActionTime = LocalDateTime.now();
        } else {
            System.out.println(name + " is not in the mood to explore right now.");
        }
    }

    public void cleanCage() {
        System.out.println("Cleaning " + name + "'s cage...");
        this.happiness += 3;
        System.out.println(name + " looks much happier in their clean cage!");
        if (cageSize > 5) {
            System.out.println("The large cage size makes " + name + " extra comfortable!");
            this.happiness += 1;
        }
    }
}