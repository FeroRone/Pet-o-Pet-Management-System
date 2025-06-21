import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class PetManager {
    private List<Pet> petCollection;
    private Scanner scanner;
    
    public PetManager() {
        petCollection = new ArrayList<>();
        scanner = new Scanner(System.in);
        petCollection = FileManager.loadPets();
    }
    
     public static void displayWelcomeMessage() {
        System.out.println(" --------------------------------------");
        System.out.println("| Welcome to the Pet Management System!|");
        System.out.println(" --------------------------------------");
    }
    
    public void managePets() {
        while (true) {
            updateAllPets();
            System.out.println("______________________________________");
            System.out.println("|         Pet Management Menu:        |");
            System.out.println("--------------------------------------");
            System.out.println("|1. Create a new pet                  |");
            System.out.println("|2. Feed a pet                        |");
            System.out.println("|3. Play with a pet                   |");
            System.out.println("|4. Check pet status                  |");
            System.out.println("|5. Make pet perform action           |");
            System.out.println("|6. Exit                              |");
            System.out.println("--------------------------------------");
            System.out.println("|Enter your choice:                   |");
            System.out.println("--------------------------------------");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createPet();
                    break;
                case "2":
                    feedPet();
                    break;
                case "3":
                    playWithPet();
                    break;
                case "4":
                    checkPetStatus();
                    break;
                case "5":
                    makePetPerformAction();
                    break;
                case "6":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
   
    public void createPet() {
        System.out.println("Select pet type:");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Hamster");
        System.out.println("4. Bird");
        System.out.print("Enter the number of your choice: ");
        
        int petTypeChoice;
        try {
            petTypeChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
    
        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();
        System.out.print("Enter favorite food: ");
        String favoriteFood = scanner.nextLine();
        
        Pet pet = null;
        
        switch (petTypeChoice) {
            case 1: // Dog
                System.out.print("Enter dog breed: ");
                String breed = scanner.nextLine();
                pet = new Dog(name, 0, breed, favoriteFood);
                break;
            
            case 2: // Cat
                System.out.print("Enter fur color: ");
                String furColor = scanner.nextLine();
                pet = new Cat(name, 0, furColor, favoriteFood);
                break;
            
            case 3: // Hamster
                System.out.print("Enter the cage size: ");
                int cageSize;
                try {
                    cageSize = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid cage size. Please enter a number.");
                    return;
                }
                pet = new Hamster(name, 0, cageSize, favoriteFood);
                break;
            
            case 4: // Bird
                System.out.print("Can the bird fly? (true/false): ");
                boolean canFly = Boolean.parseBoolean(scanner.nextLine());
                pet = new Bird(name, 0, canFly, favoriteFood);
                break;
            
            default:
                System.out.println("Invalid pet type choice.");
                return;
        }
        
        if (pet != null) {
            petCollection.add(pet);
            System.out.println(name + " has been added to your pet collection.");
        }
    }
    
    private void updateAllPets() {
        for (Pet pet : petCollection) {
            pet.updateHunger();
            pet.updateHappiness();
            pet.updateAge();
        }
    }
    
    private void feedPet() {
        if (petCollection.isEmpty()) {
            System.out.println("You don't have any pets yet.");
            return;
        }
        displayPetNames(); // Display available pet names
        System.out.print("Enter the name of the pet to feed: ");
        String petName = scanner.nextLine();
    
        Pet petToFeed = null;
        for (Pet pet : petCollection) {
            if (pet.name.equalsIgnoreCase(petName)) {
                petToFeed = pet;
                break;
            }
        }
    
        if (petToFeed == null) {
            System.out.println("Pet '" + petName + "' not found in your collection.");
            return;
        }
    
        
        if (petToFeed instanceof Hamster) {
            System.out.print("Enter the food you want to feed " + petName + ", anything other than fav food = regular food. ");
            String HamsterFavFood = scanner.nextLine();
            ((Hamster) petToFeed).feed(HamsterFavFood);
        } else if (petToFeed instanceof Dog) {
            System.out.print("Enter the food you want to feed " + petName + ", anything other than fav food = regular food. ");
            String dogFavFood = scanner.nextLine();
            ((Dog) petToFeed).feed(dogFavFood);
        } else if (petToFeed instanceof Cat) {
            System.out.print("Enter the food you want to feed " + petName + ", anything other than fav food = regular food. ");
            String catFavFood = scanner.nextLine();
            ((Cat) petToFeed).feed(catFavFood);
        } else if (petToFeed instanceof Bird) {
            System.out.print("Enter the food you want to feed " + petName + ", anything other than fav food = regular food. ");
            String birdFavFood = scanner.nextLine();
            ((Bird) petToFeed).feed(birdFavFood);
        } else {
            System.out.println("pet not found!");
        }
    }

    private void playWithPet() {
        if (petCollection.isEmpty()) {
            System.out.println("You don't have any pets yet.");
            return;
        }
        displayPetNames(); // Display available pet names
        System.out.print("Enter the name of the pet to play with: ");
        String petName = scanner.nextLine();
        for (Pet pet : petCollection) {
            if (pet.name.equalsIgnoreCase(petName)) {
                pet.play();
                return;
            }
        }
        System.out.println("Pet '" + petName + "' not found in your collection.");
    }

    private void checkPetStatus() {
        if (petCollection.isEmpty()) {
            System.out.println("You don't have any pets yet.");
            return;
        }
        displayPetNames();
        System.out.print("Enter the name of the pet to check status: ");
        String petName = scanner.nextLine();
        for (Pet pet : new ArrayList<>(petCollection)) {
            if (pet.name.equalsIgnoreCase(petName)) {
                pet.updateHunger();
                pet.updateAge(); // Update the pet's age
                pet.updateHappiness();
                pet.updateMood();
                if (pet.shouldRemove()) {
                    System.out.println(pet.name + " has starved to death due to excessive hunger.");
                    petCollection.remove(pet);
                } else {
                    pet.getStatus();
                    pet.displayHappinessChange();
                }
                return;
            }
        }
        System.out.println("Pet '" + petName + "' not found in your collection.");
    }

    private void displayPetNames() {
        if (petCollection.isEmpty()) {
            System.out.println("You don't have any pets yet.");
            return;
        }
        System.out.println("Available pets:");
        for (Pet pet : petCollection) {
            System.out.println("- " + pet.name);
        }
    }
    
    private void makePetPerformAction() {
        if (petCollection.isEmpty()) {
            System.out.println("You don't have any pets yet.");
            return;
        }   
        displayPetNames();
        System.out.print("Enter the name of the pet to perform an action: ");
        String petName = scanner.nextLine();
        for (Pet pet : petCollection) {
            if (pet.name.equalsIgnoreCase(petName)) {
                if (pet instanceof Dog) {
                    System.out.println("(------ Dog Actions ------)");
                    System.out.println("What would you do?");
                    System.out.println("1. Bark");
                    System.out.println("2. Catch Frisbee");
                    System.out.println("3. Cuddle");
                    System.out.println("4. Go for walk");
                    System.out.println("5. Fetch stick");
                    System.out.println("(-------------------------)");
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        ((Dog) pet).makeSound();
                    } else if (choice.equals("2")) {
                        ((Dog) pet).catchFrisbee();
                    } else if (choice.equals("3")) {
                        ((Dog) pet).cuddle();
                    } else if (choice.equals("4")) {
                        ((Dog) pet).goForWalk();
                    } else if (choice.equals("5")) {
                        ((Dog) pet).fetchStick();
                    } else
                        System.out.println("Unknown input");
                } else if (pet instanceof Cat) {
                    System.out.println("(------ Cat Actions ------)");
                    System.out.println("What would you do?");
                    System.out.println("1. Meow");
                    System.out.println("2. Catch a mouse");
                    System.out.println("3. Cuddle");
                    System.out.println("4. Climb a tree");
                    System.out.println("5. Chase a string");
                    System.out.println("(-------------------------)");
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        ((Cat) pet).makeSound();
                    } else if (choice.equals("2")) {
                        ((Cat) pet).catchMouse();
                    } else if (choice.equals("3")) {
                        ((Cat) pet).cuddle();
                    } else if (choice.equals("4")) {
                        ((Cat) pet).climbTree();
                    } else if (choice.equals("5")) {
                        ((Cat) pet).chaseString();
                    }
                } else if (pet instanceof Hamster) {
                    System.out.println("(------ Hamster Actions ------)");
                    System.out.println("What would you do?");
                    System.out.println("1. Squeaks");
                    System.out.println("2. Rolling threadmmil");
                    System.out.println("3. Burrow");
                    System.out.println("4. Explore obstacle course");
                    System.out.println("5. Clean Cage");
                    System.out.println("(-------------------------)");
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        ((Hamster) pet).makeSound();
                    } else if (choice.equals("2")) {
                        ((Hamster) pet).rolling();
                    } else if (choice.equals("3")) {
                        ((Hamster) pet).burrow();
                    } else if (choice.equals("4")) {
                        ((Hamster) pet).exploreObstaclesCourse();
                    } else if (choice.equals("5")) {
                        ((Hamster) pet).cleanCage();
                    }
                } else if (pet instanceof Bird) {
                    System.out.println("(------ Bird Actions ------)");
                    System.out.println("What would you do?");
                    System.out.println("1. Chirp");
                    System.out.println("2. Fly");
                    System.out.println("3. Sing Melody");
                    System.out.println("4. Bath in Water");
                    System.out.println("5. Let Out of Cage");
                    System.out.println("(-------------------------)");
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        ((Bird) pet).makeSound();
                    } else if (choice.equals("2")) {
                        ((Bird) pet).fly();
                    } else if (choice.equals("3")) {
                        ((Bird) pet).singMelody();
                    } else if (choice.equals("4")) {
                        ((Bird) pet).bathInWater();
                    } else if (choice.equals("5")) {
                        if (((Bird) pet).letOutOfCage()) {
                            System.out.println(pet.name + " has flown away and won't be coming back.");
                            petCollection.remove(pet);
                            return;
                        }
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }

                } else {
                    System.out.println("This pet type does not have a specific action.");
                }
                return;
            }
        }
        System.out.println("Pet '" + petName + "' not found in your collection.");
    }
    public void savePets() {
        FileManager.savePets(petCollection);
    }
}

