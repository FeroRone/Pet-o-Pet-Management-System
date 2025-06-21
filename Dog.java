import java.time.LocalDateTime;
import java.io.*;

class Dog extends Pet {
    public String breed;

    public Dog(String name, int age, String breed, String favoriteFood) {
        super(name, age, favoriteFood); // Pass birthdate to the parent constructor
        this.breed = breed;
    }
    
    public void feed(String dogFavFood) {
        this.hunger = 0;
        this.lastFedTime = LocalDateTime.now();

        if (dogFavFood.equalsIgnoreCase(favoriteFood)) {
            this.happiness += 4; // Increase happiness more when fed favorite food
            System.out.println(name + " Favourite food! Extra happiness gained.");
        } else {
            this.happiness += 2; // Default happiness increase
        } 

        System.out.println(name + " has been fed.");
    }
    
    public void catchFrisbee() {
        if (canPerformAction()) {
            double random = Math.random();
            if (random < 1.0 / 2.0) {
                System.out.println(name + " caught Frisbee!");
                this.happiness += 1;
            } else {
                System.out.println(name + " tried to catch the frisbee but missed.");
                this.happiness -= 1;
            }
            lastPlayedOrActionTime = LocalDateTime.now(); // Update the last action time
        } else {
            System.out.println(name + " is not ready to catch the frisbee yet.");
        }
    }
    
    public void goForWalk() {
        updateMood();
        if (mood.equals("Sad")) {
            System.out.println(name + " is too sad to go for a walk. Try cheering them up first!");
            return;
        }
        if (canPerformAction()) {
            System.out.println(name + " goes for a walk and gets some exercise!");
            this.happiness += 2;
            this.hunger += 1;
            lastPlayedOrActionTime = LocalDateTime.now();
            if (mood.equals("Happy")) {
                System.out.println(name + " had an extra fun time on the walk!");
                this.happiness += 1;
            }
        } else {
            System.out.println(name + " is too tired to go for a walk right now.");
        }
    }
    
    public void fetchStick() {
        updateMood();
        if (mood.equals("Sad")) {
            System.out.println(name + " doesn't feel like playing fetch. Maybe try something else to improve their mood?");
            return;
        }
        if (canPerformAction()) {
            double successChance = Math.random();
            if (successChance > 0.3) {
                System.out.println(name + " successfully fetches the stick and brings it back!");
                this.happiness += 2;
            } else {
                System.out.println(name + " chases the stick but gets distracted by a squirrel.");
                this.happiness += 1;
            }
            this.hunger += 1;
            lastPlayedOrActionTime = LocalDateTime.now();
        } else {
            System.out.println(name + " is not in the mood to fetch right now.");
        }
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }

}