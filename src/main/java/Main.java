import repository.GsonDeveloperRepositoryImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        sayHello();
        chooseFirstAction();
    }

    public static void sayHello() {
        System.out.println("\nHello, choose an action:\n" +
                "1. Show all Developers. \n" +
                "2. Show all Skills. \n" +
                "3. Show all Specialties. \n" +
                "4. Check connection to the file \"developers.json.\" \n" +
                "0. exit the program.\n");
    }

    public static void chooseFirstAction() {
        boolean correctInput;
        String chooseFirstAction;
        Scanner sc = new Scanner(System.in);

        do {
            correctInput = true;
            chooseFirstAction = sc.nextLine();
            switch (chooseFirstAction) {
                case "1" -> System.out.println("Your choice is 1.");
                case "2" -> System.out.println("Your choice is 2.");
                case "3" -> System.out.println("Your choice is 3.");
                case "4" -> checkConnectionToFileDevelopersJson();
                case "0" -> System.exit(0);
                default -> {
                    System.out.println("Wrong input. Please, enter a digit from 0 to 4.");
                    correctInput = false;
                }
            }
        } while (!correctInput);
        sc.close();
    }

    public static void checkConnectionToFileDevelopersJson() {
        GsonDeveloperRepositoryImpl gsonDeveloperRepository = new GsonDeveloperRepositoryImpl();
        gsonDeveloperRepository.checkConnectionToRepositoryFile();
    }
}

