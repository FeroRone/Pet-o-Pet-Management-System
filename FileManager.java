import java.io.*;
import java.util.*;
import java.time.*;

public class FileManager {
    private static final String FILE_NAME = "pets.txt";

    public static void savePets(List<Pet> pets) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Pet pet : pets) {
                String line = pet.getClass().getSimpleName() + ";" +
                              pet.name + ";" +
                              pet.age + ";" +
                              pet.hunger + ";" +
                              pet.happiness + ";" +
                              pet.favoriteFood + ";" +
                              System.currentTimeMillis();

                // Add specific attributes for each pet type
                if (pet instanceof Dog) {
                    line += ";" + ((Dog) pet).breed;
                } else if (pet instanceof Cat) {
                    line += ";" + ((Cat) pet).furColor;
                } else if (pet instanceof Hamster) {
                    line += ";" + ((Hamster) pet).cageSize;
                } else if (pet instanceof Bird) {
                    line += ";" + ((Bird) pet).canFly;
                }

                writer.println(line);
            }
            System.out.println("Pets saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving pets: " + e.getMessage());
        }
    }

    public static List<Pet> loadPets() {
        List<Pet> pets = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line, ";");
                    if (st.countTokens() < 7) continue;  // Skip invalid lines
                    
                    String type = st.nextToken();
                    String name = st.nextToken();
                    int age = Integer.parseInt(st.nextToken());
                    int hunger = Integer.parseInt(st.nextToken());
                    int happiness = Integer.parseInt(st.nextToken());
                    String favoriteFood = st.nextToken();
                    long savedTime = Long.parseLong(st.nextToken());
                    
                    String specificAttribute = st.hasMoreTokens() ? st.nextToken() : "";
                    
                    Pet pet = null;
                    switch (type) {
                        case "Dog":
                            pet = new Dog(name, age, specificAttribute, favoriteFood);
                            break;
                        case "Cat":
                            pet = new Cat(name, age, specificAttribute, favoriteFood);
                            break;
                        case "Hamster":
                            pet = new Hamster(name, age, Integer.parseInt(specificAttribute), favoriteFood);
                            break;
                        case "Bird":
                            pet = new Bird(name, age, Boolean.parseBoolean(specificAttribute), favoriteFood);
                            break;
                    }
                    
                    if (pet != null) {
                        pet.hunger = hunger;
                        pet.happiness = happiness;
                        pet.lastFedTime = LocalDateTime.now().minusSeconds((System.currentTimeMillis() - savedTime) / 1000);
                        pets.add(pet);
                    }
                }
                System.out.println("Pets loaded successfully.");
            } catch (IOException e) {
                System.out.println("Error loading pets: " + e.getMessage());
            }
        }
        
        return pets;
    }
}