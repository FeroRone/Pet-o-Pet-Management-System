public class petMain
{
   public static void main(String[] args)
   {
    PetManager petManager = new PetManager();
    petManager.displayWelcomeMessage();
    petManager.managePets();
    petManager.savePets();
   }
}
