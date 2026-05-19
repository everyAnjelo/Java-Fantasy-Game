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
    // public int score;
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
        expeditionManager = new ExpeditionManager(this);


        guildName = "";
        totalExpeditions = 0;
        expeditionsCompleted = 0;
        difficulty = Difficulty.EASY;

        gold = 0;
        totalGoldGained = 0;
        // score = 0;
        isRunning = true;


    }

    public void startGame() {

        guildName = askGuildName();
        totalExpeditions = askExpeditionCount();
        difficulty = askDifficulty();

        if (difficulty == Difficulty.EASY) {
            gold = 100000;

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

        if (adventurerManager.getMainTeam().isEmpty()) {
            System.out.println("You need at least one adventurer to go on an expedition.");
            return;
        }

        expeditionManager.createAllEvents();
        expeditionManager.generatePossibleExpeditions();

        System.out.println();
        System.out.println("=== Choose an Expedition ===");

        for (int i = 0; i < expeditionManager.getExpeditions().size(); i++) {
            Expedition expedition = expeditionManager.getExpeditions().get(i);

            System.out.println((i + 1) + ". " + expedition.getName());
            System.out.println("Description: " + expedition.getDescription());
            System.out.println("Stamina Required: " + expedition.getStaminaRequired());
            System.out.println("Perception Required: " + expedition.getPerceptionRequired());
            System.out.println("Reward: " + expedition.getReward());
            System.out.println();
        }

        System.out.print("Choose expedition: ");
        int choice = readInt();

        if (choice < 1 || choice > expeditionManager.getExpeditions().size()) {
            System.out.println("Invalid expedition choice.");
            return;
        }

        Expedition chosenExpedition = expeditionManager.getExpeditions().get(choice - 1);
        expeditionManager.setCurrentExpedition(chosenExpedition);

        expeditionManager.setTotalingEarnings(0);

        expeditionManager.startExpedition(adventurerManager.getMainTeam());

        System.out.println();
        System.out.println("You started: " + chosenExpedition.getName());
        System.out.println("Events to complete: " + expeditionManager.getNumberOfEvents());

        while (expeditionManager.getNumberOfEvents() > 0) {

            expeditionManager.nextEvent(adventurerManager.getMainTeam());

            Event event = expeditionManager.getCurrentEvent();

            System.out.println();
            System.out.println("=== Event ===");
            System.out.println(event.getDescription());
            System.out.println();
            System.out.println("1. " + event.getOptionOne());
            System.out.println("2. " + event.getOptionTwo());
            System.out.print("Choose: ");

            int eventChoice = readInt();

            if (eventChoice == 1) {
                event.chooseOptionOne();
                System.out.println(event.successMessage());

            } else if (eventChoice == 2) {
                event.chooseOptionTwo();
                System.out.println("You avoided the danger and moved on.");

            } else {
                System.out.println("Invalid choice. Your party wasted time.");
            }

            expeditionManager.setNumberOfEvents(expeditionManager.getNumberOfEvents() - 1);

            System.out.println("Events left: " + expeditionManager.getNumberOfEvents());
        }

        expeditionManager.endExpedition(adventurerManager.getMainTeam());

        expeditionsCompleted++;

        guildHall.replaceAdventurersAfterExpedition(expeditionsCompleted);

        expeditionManager.setCurrentExpedition(null);
        expeditionManager.setExpeditions(null);

        System.out.println();
        System.out.println("Expedition completed!");
        System.out.println("Gold earned from events: " + expeditionManager.getTotalingEarnings());
        System.out.println("Current gold: " + gold);
        System.out.println("Expeditions completed: " + expeditionsCompleted + "/" + totalExpeditions);
    }
    private void guildOverview() {
        boolean viewing = true;

        while (viewing && isRunning) {
            System.out.println();
            System.out.println("=== Guild Overview ===");
            System.out.println("1. View adventurers and inventory");
            System.out.println("2. Move reserve adventurer to main party");
            System.out.println("3. Move main adventurer to reserve");
            System.out.println("4. Retire adventurer");
            System.out.println("5. Use/equip item");
            System.out.println("6. Leave overview");
            System.out.print("Choose: ");

            int choice = readInt();

            if (choice == 1) {
                displayGuild();
            } else if (choice == 2) {
                moveReserveToMain();
            } else if (choice == 3) {
                moveMainToReserve();
            } else if (choice == 4) {
                retireAdventurer();
            } else if (choice == 5) {
                useItem();
            } else if (choice == 6) {
                viewing = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    private void retireAdventurer(){
        adventurerManager.displayMainTeam();
        adventurerManager.displayReserveTeam();

        System.out.println("Retire from:");
        System.out.println("1. Main party");
        System.out.println("2. Reserve party");
        System.out.print("Choose: ");

        int listChoice = readInt();

        if (listChoice == 1) {
            System.out.print("Choose main adventurer: ");
            int index = readInt();

            if (index >= 1 && index <= adventurerManager.getMainTeam().size()) {
                Adventurer adventurer = adventurerManager.getMainTeam().get(index - 1);
                adventurerManager.retireAdventurer(adventurer);
            } else {
                System.out.println("Invalid adventurer.");
            }

        } else if (listChoice == 2) {
            System.out.print("Choose reserve adventurer: ");
            int index = readInt();

            if (index >= 1 && index <= adventurerManager.getReserveTeam().size()) {
                Adventurer adventurer = adventurerManager.getReserveTeam().get(index - 1);
                adventurerManager.retireAdventurer(adventurer);
            } else {
                System.out.println("Invalid adventurer.");
            }

        } else {
            System.out.println("Invalid choice.");
        }

    }

    private void useItem() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        inventory.displayItems();
        System.out.print("Choose item: ");
        int itemChoice = readInt();

        if (itemChoice < 1 || itemChoice > inventory.getSize()) {
            System.out.println("Invalid item.");
            return;
        }

        Item item = inventory.getItem(itemChoice - 1);

        System.out.println("Use item on:");
        System.out.println("1. Main party adventurer");
        System.out.println("2. Reserve adventurer");
        System.out.print("Choose: ");

        int listChoice = readInt();

        Adventurer adventurer = null;

        if (listChoice == 1) {
            adventurerManager.displayMainTeam();

            System.out.print("Choose adventurer: ");
            int advChoice = readInt();

            if (advChoice >= 1 && advChoice <= adventurerManager.getMainTeam().size()) {
                adventurer = adventurerManager.getMainTeam().get(advChoice - 1);
            }

        }
        else if (listChoice == 2) {
            adventurerManager.displayReserveTeam();

            System.out.print("Choose adventurer: ");
            int advChoice = readInt();

            if (advChoice >= 1 && advChoice <= adventurerManager.getReserveTeam().size()) {
                adventurer = adventurerManager.getReserveTeam().get(advChoice - 1);
            }
        }

        if (adventurer == null) {
            System.out.println("Invalid adventurer.");
            return;
        }

        if (!item.canBeUsedBy(adventurer)) {
            System.out.println(item.getName() + " cannot be used by " + adventurer.getRole());
            return;
        }

        if (item.getType() == ItemType.POTION) {
            adventurer.usePotion(item);
            inventory.removeItem(item);
        } else {
            adventurer.equipWeapon(item);
            inventory.removeItem(item);
        }

    }

    private void moveMainToReserve() {
        adventurerManager.displayReserveTeam();

        if (adventurerManager.getReserveTeam().isEmpty()) {
            return;
        }

        System.out.print("Choose reserve adventurer: ");
        int index = readInt();

        if (index >= 1 && index <= adventurerManager.getReserveTeam().size()) {
            Adventurer adventurer = adventurerManager.getReserveTeam().get(index - 1);
            adventurerManager.addToMain(adventurer);
        } else {
            System.out.println("Invalid choice.");
        }    }

    private void moveReserveToMain() {
        adventurerManager.displayMainTeam();

        System.out.print("Choose main adventurer: ");
        int index = readInt();

        if (index >= 1 && index <= adventurerManager.getMainTeam().size()) {
            Adventurer adventurer = adventurerManager.getMainTeam().get(index - 1);
            adventurerManager.addToReserve(adventurer);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void displayGuild() {
        adventurerManager.displayMainTeam();
        adventurerManager.displayReserveTeam();
        inventory.displayItems();
    }

    private void openMarket() {
        boolean shopping = true;

        while (shopping && isRunning) {
            System.out.println();
            System.out.println("=== Market ===");
            System.out.println("Gold: " + gold);
            System.out.println("1. Buy item");
            System.out.println("2. Sell item");
            System.out.println("3. Leave market");
            System.out.print("Choose: ");

            int choice = readInt();

            if (choice == 1) {
                buyItem();

                if (gold <= 0) {
                    gold = 0;
                    System.out.println("Your guild ran out of gold.");
                    finishGame(false);
                    return;
                }

            }
            else if (choice == 2) {
                sellItem();
            }

            else if (choice == 3) {
                shopping = false;
            }

            else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public void buyItem() {
        shop.displayItems();

        System.out.print("Choose item to buy: ");
        int choice = readInt();

        if (choice < 1 || choice > shop.getAvailableItems().size()) {
            System.out.println("Invalid item.");
            return;
        }

        Item item = shop.getAvailableItems().get(choice - 1);
        shop.buyItem(item, this);
    }

    public void sellItem() {
        inventory.displayItems();

        if (inventory.isEmpty()) {
            return;
        }

        System.out.print("Choose item to sell: ");
        int choice = readInt();

        if (choice < 1 || choice > inventory.getSize()) {
            System.out.println("Invalid item.");
            return;
        }

        Item item = inventory.getItem(choice - 1);
        shop.sellItem(item, this);
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
            }
            else if (choice >= 1 && choice <= guildHall.getAvailableAdventurers().size()) {
                hireAdventurer(choice);

                if (gold <= 0) {
                    gold = 0;
                    System.out.println("Your guild ran out of gold.");
                    finishGame(false);
                    return;
                }

            }
            else {
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

        // thinking if we should remove or not on shop the adventurer
        //guildHall.getAvailableAdventurers().remove(choice - 1);

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
        // System.out.println("Score: " + score);

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

