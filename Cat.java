import java.time.LocalDateTime;
import java.io.*;

class Cat extends Pet {
    public String furColor;

    public Cat(String name, int age, String furColor, String favoriteFood) {
        super(name, age, favoriteFood); // Pass favoriteFood to the parent constructor
        this.furColor = furColor;
    }

    public void feed(String catFavFood) {
        this.hunger = 0;
        this.lastFedTime = LocalDateTime.now();

        if (catFavFood.equalsIgnoreCase(favoriteFood)) {
            this.happiness += 4; // Increase happiness more when fed favorite food
            System.out.println(name + " Favourite food! Extra happiness gained.");
        } else {
            this.happiness += 2; // Default happiness increase
        }

        System.out.println(name + " has been fed.");
    }
    
    public void catchMouse() {
        if (canPerformAction()) {
            double random = Math.random(); // Generate a random value between 0 and 1
            if (random < 1.0 / 3.0) {
                System.out.println(name + " caught a mouse!");
                this.hunger -= 3;
                if (this.hunger <= 0) {
                    this.hunger = 0;
                }
            } else {
                System.out.println(name + " tried to catch a mouse but missed.");
            }
            lastPlayedOrActionTime = LocalDateTime.now(); // Update the last action time
        } else {
            System.out.println(name + " is not ready to catch a mouse yet.");
        }
    }
    
    public void climbTree() {
        updateMood();
        if (mood.equals("Sad")) {
            System.out.println(name + " doesn't feel like climbing. Maybe try cheering them up first?");
            return;
        }
        if (canPerformAction()) {
            System.out.println(name + " climbs a tree and surveys their domain!");
            this.happiness += 2;
            this.hunger += 1;
            lastPlayedOrActionTime = LocalDateTime.now();
            if (mood.equals("Happy")) {
                System.out.println(name + " had an extra exciting time climbing!");
                this.happiness += 1;
            }
        } else {
            System.out.println(name + " is too tired to climb right now.");
        }
    }

    public void chaseString() {
        updateMood();
        if (mood.equals("Sad")) {
            System.out.println(name + " isn't interested in the string. Try something else to improve their mood?");
            return;
        }
        if (canPerformAction()) {
            double successChance = Math.random();
            if (successChance > 0.2) {
                System.out.println(name + " pounces on the string with great enthusiasm!");
                this.happiness += 2;
            } else {
                System.out.println(name + " chases the string but gets distracted by a sunbeam.");
                this.happiness += 1;
            }
            this.hunger += 1;
            lastPlayedOrActionTime = LocalDateTime.now();
        } else {
            System.out.println(name + " is not in the mood to chase right now.");
        }
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow!");
    }
}