package seng201.team0;

import java.util.Random;
import java.util.Scanner;

public class GameEnvironment {
    public String guildName;
    public int totalExpeditions;
    public int expeditionsCompleted;
    public Difficulty difficulty;

    public double gold;
    public double totalGoldGained;
    public int score;
    public boolean isRunning;

    public AdventurerManager adventurerManager;
    public ExpeditionManager expeditionManager;
    public Shop shop;
    public Inventory inventory;
    public GuildHall guildHall;

    public Scanner scanner;
    public Random random;

    public GameEnvironment() {
        scanner = new Scanner(System.in);
        random = new Random();
        adventurerManager = new AdventurerManager();
        shop = new Shop();
        guildHall = new GuildHall();
        inventory = new Inventory();


        guildName = "";
        totalExpeditions = 0;
        expeditionsCompleted = 0;
        difficulty = Difficulty.EASY;

        gold = 0;
        totalGoldGained = 0;
        score = 0;
        isRunning = true;


    }

    public void startGame() {

        guildName = askGuildName();
        totalExpeditions = askExpeditionCount();
        difficulty = askDifficulty();

        if (difficulty == Difficulty.EASY) {
            gold = 500;

        } else {
            gold = 350;

        }

        System.out.println();
        System.out.println("Welcome, " + guildName + " Guild!");
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Starting gold: " + gold);
        System.out.println("Total expeditions to play: " + totalExpeditions);

        chooseStartingAdventurers();

        while (isRunning) {
            if (expeditionsCompleted >= totalExpeditions) {
                finishGame(true);
                return;


            }
            if (gold <= 0) {
                finishGame(false);
                System.out.println("You have no gold left.");
                return;

            }
            if (adventurerManager.getMainTeam().isEmpty() && gold < guildHall.getCheapestHiringCost()) {
                System.out.println("You have no adventurers and not enough gold to hire more.");
                finishGame(false);
                return;
            }

            showMainMenu();

            int choice = readInt();

            if (choice == 1) {
                viewGameStatus();
            } else if (choice == 2) {
                openGuildHall();
            } else if (choice == 3) {
                openMarket();
            } else if (choice == 4) {
                guildOverview();
            } else if (choice == 5) {
                goOnExpedition();
            } else if (choice == 6) {
                System.out.println("You chose to abandon the guild.");
                finishGame(false);
            } else {
                System.out.println("Invalid choice.");

            }
        }

    }

    private Difficulty askDifficulty() {
        while (true) {
            System.out.println("Choose difficulty:");
            System.out.println("1. Easy");
            System.out.println("2. Hard");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    return Difficulty.EASY;

                case 2:
                    return Difficulty.HARD;

                default:
                    System.out.println("Invalid choice.");

            }
        }
    }

    private int askExpeditionCount() {
        while (true) {
            System.out.print("Enter number of expeditions, 5 to 15: ");
            int count = readInt();

            if (count >= 5 && count <= 15) {
                return count;
            } else {
                System.out.println("Expedition count must be from 5 to 15.");
            }
        }

    }

    private String askGuildName() {
        while (true) {
            System.out.println("Enter guild name, 3 to 15 letters/numbers only: ");
            String name = scanner.nextLine();
            if (name.length() < 3 || name.length() > 15) {
                System.out.println("Guild name must be between 3 and 15 characters.");

            } else if (!name.matches("[a-zA-Z0_9]+")) {
                System.out.println("Guild name cannot contain spaces or special characters.");
            } else {
                return name;
            }
        }
    }

    private void goOnExpedition() {
    }

    private void guildOverview() {
    }

    private void openMarket() {
    }

    private void openGuildHall() {
        boolean hiring = true;

        while (hiring && isRunning) {
            System.out.println();
            System.out.println("=== Guild Hall ===");
            System.out.println("Gold: " + gold);
            guildHall.displayAvailableAdventurers();
            System.out.println((guildHall.getAvailableAdventurers().size() + 1) + ". Leave Guild Hall");
            System.out.print("Choose: ");

            int choice = readInt();

            if (choice == guildHall.getAvailableAdventurers().size() + 1) {
                hiring = false;
            } else if (choice >= 1 && choice <= guildHall.getAvailableAdventurers().size()) {
                hireAdventurer(choice);

                if (gold <= 0) {
                    gold = 0;
                    System.out.println("Your guild ran out of gold.");
                    finishGame(false);
                    return;
                }

            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void hireAdventurer(int choice) {
        if (choice < 1 || choice > guildHall.getAvailableAdventurers().size()) {
            System.out.println("Invalid adventurer.");
            return;
        }

        Adventurer adventurer = guildHall.getAvailableAdventurers().get(choice - 1);

        System.out.print("Enter adventurer name: ");
        String name = scanner.nextLine();
        if (!name.isBlank()) {
            adventurer.setName(name);
        }

        if (gold < adventurer.getHiringCost()) {
            System.out.println("Not enough gold to hire " + adventurer.getName() + ".");
            return;
        }

        gold -= adventurer.getHiringCost();
        guildHall.getAvailableAdventurers().remove(choice - 1);

        if (adventurerManager.getMainTeam().size() < adventurerManager.getMaxMainTeam()) {
            adventurerManager.addToMain(adventurer);
        } else {
            adventurerManager.addToReserve(adventurer);
        }

        System.out.println(adventurer.getName() + " was hired.");
    }

    private void viewGameStatus() {
        System.out.println();
        System.out.println("=== Game Status ===");
        System.out.println("Guild: " + guildName);
        System.out.println("Gold: " + gold);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Expeditions completed: " + expeditionsCompleted + "/" + totalExpeditions);
        System.out.println("Expeditions remaining: " + (totalExpeditions - expeditionsCompleted));
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("=== Main Menu ===");
        System.out.println("1. View game status");
        System.out.println("2. Go to Guild Hall");
        System.out.println("3. Go to Market");
        System.out.println("4. Guild Overview");
        System.out.println("5. Go on Expedition");
        System.out.println("6. Exit Game");
        System.out.print("Choose: ");
    }


    private void finishGame(boolean won) {
        System.out.println();
        System.out.println("=== Game Finished ===");
        System.out.println("Guild Name: " + guildName);

        if (won) {
            System.out.println("Result: You won! You completed all expeditions.");
        } else {
            System.out.println("Result: You lost.");
        }

        System.out.println("Selected expeditions: " + totalExpeditions);
        System.out.println("Expeditions completed: " + expeditionsCompleted);
        System.out.println("Final gold: " + gold);
        System.out.println("Total gold gained: " + totalGoldGained);
        System.out.println("Score: " + score);

        isRunning = false;
    }

    private void chooseStartingAdventurers() {
        System.out.println();
        System.out.println("=== Choose 3 Starting Adventurers ===");
        System.out.println("Starting adventurers are free.");

        while(adventurerManager.getMainTeam().size() < 3){
            guildHall.displayAdventurerTypes();

            System.out.print("Choose adventurer " + (adventurerManager.getMainTeam().size() + 1) + " of 3: ");
            int choice = readInt();

            System.out.print("Enter adventurer name: ");
            String name = scanner.nextLine();

            Adventurer adventurer = guildHall.createAdventurer(choice, name);

            if (adventurer == null) {
                System.out.println("Invalid adventurer choice.");
            } else {
                adventurerManager.addToMain(adventurer);
                System.out.println(adventurer.getName() + " joined your starting party for free.");
            }

        }

    }

    private int readInt() {
        while (true) {
            try {
                int value;
                value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        }
    }
}

