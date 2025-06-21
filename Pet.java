import java.time.LocalDateTime;
import java.time. temporal.ChronoUnit;
import java.io.*;

abstract class Pet {
    protected String name;
    protected int age;
    protected int hunger;
    protected int happiness;
    protected int previousHappiness;
    public LocalDateTime lastFedTime;
    public LocalDateTime birthDate;
    protected LocalDateTime lastPlayedOrActionTime;
    protected LocalDateTime lastHappinessUpdateTime;
    protected String favoriteFood;
    protected String mood;
    

    public Pet(String name, int age, String favoriteFood) {
        this.name = name;
        this.age = age;
        this.hunger = 0;
        this.happiness = 5;
        this.previousHappiness = this.happiness;
        this.lastFedTime = LocalDateTime.now();
        this.birthDate = LocalDateTime.now().minusYears(age);
        this.lastHappinessUpdateTime = LocalDateTime.now();
        this.favoriteFood = favoriteFood;
        this.lastPlayedOrActionTime = null;
    }
    
    public abstract void makeSound();
    
    //play method
    public void  play() {
        if (canPerformAction()) {
            this.previousHappiness = this.happiness;
            this.happiness++;
            this.lastPlayedOrActionTime = LocalDateTime.now();
            this.lastHappinessUpdateTime = LocalDateTime.now();
            updateMood();
            if (mood.equals("Sad")) {
                System.out.println(name + " played, but still seems a bit down.");
            } else if (mood.equals("Normal")) {
                System.out.println(name + " enjoyed playing with you.");
            } else {
                System.out.println(name + " had a great time playing! They're very happy!");
            }
        } else {
            System.out.println("You need to wait before playing with " + name + " again.");
        }
    }
    
        public void cuddle() {
        updateMood();
        System.out.println(name + " cuddles up for some affection.");
        if (mood.equals("Sad")) {
            System.out.println("The cuddle session really cheers " + name + " up!");
            this.happiness += 3;
        } else {
            System.out.println(name + " enjoys the cuddle and feels loved.");
            this.happiness += 1;
        }
        this.lastPlayedOrActionTime = LocalDateTime.now();
    }
    
    public boolean canPerformAction() {
        if (lastPlayedOrActionTime == null) {
            return true; // Allow the first action
        }
        
        long minutesSinceLastAction = ChronoUnit.MINUTES.between(lastPlayedOrActionTime, LocalDateTime.now());
        return minutesSinceLastAction >= 1; // Adjust the cooldown duration (e.g., 10 minutes) as needed
    }
    
    //status method
    public void getStatus() {
        System.out.println("(------ Pet Status ------)");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Hunger: " + hunger);
        System.out.println("Happiness: " + happiness);
        System.out.println("Mood: " + mood);
        System.out.println("Favourite Food: " + favoriteFood);
    
        if (this instanceof Dog) {
            System.out.println("Breed: " + ((Dog) this).breed);
        } else if (this instanceof Cat) {
            System.out.println("Fur Color: " + ((Cat) this).furColor);
        } else if (this instanceof Hamster) {
            System.out.println("Cage Size: " + ((Hamster) this).cageSize);
        } else if (this instanceof Bird) {
            System.out.println("Can Fly: " + ((Bird) this).canFly);
        }
        
        System.out.println("(-------------------------)");
    }
    
    public void displayHappinessChange(int oldHappiness) {
        if (happiness < oldHappiness) {
            System.out.println(name + "'s happiness has decreased. They might need some attention!");
        }
    }
    
    protected void updateMood() {
        if (happiness < 10) {
            mood = "Sad";
        } else if (happiness < 20) {
            mood = "Normal";
        } else {
            mood = "Happy";
        }
    }
    
    public void updateAge() {
        long daysElapsed = ChronoUnit.DAYS.between(birthDate, LocalDateTime.now());
        this.age = (int) (daysElapsed / 365);
        this.birthDate = LocalDateTime.now().minusDays(daysElapsed % 365);
    }
    
    public void updateHappiness() {
        LocalDateTime now = LocalDateTime.now();
        long minutesElapsed = ChronoUnit.MINUTES.between(lastHappinessUpdateTime, now);
        
        if (minutesElapsed > 0) {
            this.previousHappiness = this.happiness;
            int decrease = (int) Math.min(minutesElapsed, 5); // Cap at 5 to prevent large decreases
            this.happiness -= decrease;
            if (this.happiness < 0) this.happiness = 0;
            lastHappinessUpdateTime = now;
            updateMood();
        }
    }
    
    public void displayHappinessChange() {
        if (this.happiness < this.previousHappiness) {
            System.out.println(name + "'s happiness has decreased from " + 
                               previousHappiness + " to " + happiness + 
                               ". They might need some attention!");
        }
    }
    
    //method to increase pet hunger
    public void updateHunger() {
        long secondsElapsed = ChronoUnit.SECONDS.between(lastFedTime, LocalDateTime.now());
        this.hunger += secondsElapsed / 30;  // Increment hunger every 30 seconds
        this.lastFedTime = LocalDateTime.now().minusSeconds(secondsElapsed % 30);  // Adjust last fed time to account for leftover seconds
    }
    
    public boolean shouldRemove() {
        return hunger >= 10;
    }
}