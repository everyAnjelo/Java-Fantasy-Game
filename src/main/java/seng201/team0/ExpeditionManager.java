package seng201.team0;

import java.util.ArrayList;
import java.util.Random;


public class ExpeditionManager {
    protected final int BASE_PERCEPTION_SCORE = 60;
    protected final int BASE_STAMINA_SCORE = 250;
    protected final int BASE_EXPEDITION_ARRAY_SIZE = 3;
    private ArrayList<Expedition> Expeditions;

    //The current Expedition the player has chosen and is working on
    private Expedition currentExpedition;
    private ArrayList<String> expeditionNames;
    private ArrayList<String> expeditionDescriptions;
    private int numberOfExpeditions = 0;

    //Constructor for ExpeditionManager for if hard coding Expedition array is required, should use generateExpeditions
    public ExpeditionManager(ArrayList<Expedition> Expeditions) {
        this.Expeditions = Expeditions;
    }


    //Getter methods
    public ArrayList<Expedition> getExpeditions() {
        return Expeditions;
    }

    public Expedition getCurrentExpedition() {
        return currentExpedition;
    }

    public int getNumberOfExpeditions() {
        return numberOfExpeditions;
    }

    //Setter Methods
    public void setExpeditions(ArrayList<Expedition> Expeditions) {
        this.Expeditions = Expeditions;
    }

    public void setCurrentExpedition(Expedition currentExpedition) {
        this.currentExpedition = currentExpedition;
    }

    public void setNumberOfExpeditions(int numberOfExpeditions) {
        this.numberOfExpeditions = numberOfExpeditions;
    }

    public void setExpeditionNames(ArrayList<String> expeditionNames) {
        this.expeditionNames = expeditionNames;
    }

    public void setExpeditionDescriptions(ArrayList<String> expeditionDescriptions) {
        this.expeditionDescriptions = expeditionDescriptions;
    }
    public void createNamesAndDescriptions()
    {
        ArrayList<String> tempDescriptions = new ArrayList<String>();
        tempDescriptions.add("A large rotting swamp, filled with stagnant water and the stench of death");
        tempDescriptions.add("A lush forest, trees as far as the eye can see, filled with wildlife");
        tempDescriptions.add("A mountain path filled with rocks and debris, stretching into the horizon");

        ArrayList<String> tempNames = new ArrayList<String>();
        tempNames.add("Death Swamp");
        tempNames.add("Mountain Path");
        tempNames.add("Mystical Forest");

        setExpeditionDescriptions(tempDescriptions);
        setExpeditionNames(tempNames);
    }

    //Generates Random Expeditions for the Player to choose
    public void generatePossibleExpeditions() {
        //set the names and descriptions for the expeditions
        createNamesAndDescriptions();

        //Generate random Numbers for the expedition stats
        Random r = new Random();

        //Counter for number of Expeditions put into the array

        if (this.getCurrentExpedition() == null) {
            //Temporary list for the Expeditions
            ArrayList<Expedition> tempExpeditionList = new ArrayList<>();
            int i = 0;
            while (tempExpeditionList.size() < BASE_EXPEDITION_ARRAY_SIZE) {
                i += 1;
                //Code for randomly generating Perception and Stamina requirements for Expeditions
                int addToPerception = (r.nextInt(10) + 1) * numberOfExpeditions;
                int addToStamina = (r.nextInt(10) + 1) * numberOfExpeditions;

                //Code required for generating names and descriptions, possibly from a pre generated text file
                int randomIndexForStrings = r.nextInt(1,4);

                String newName = expeditionNames.get(randomIndexForStrings);
                String newDescription = expeditionDescriptions.get(randomIndexForStrings);


                Expedition newExpedition = new Expedition(newName,
                        newDescription,
                        (addToStamina * 15) + BASE_STAMINA_SCORE,
                        (addToPerception * 5) + BASE_PERCEPTION_SCORE,
                        (addToPerception + addToStamina) * 2);
                //Adds the New Expedition to the temporary Expedition list
                tempExpeditionList.add(newExpedition);

            }
            //Sets the temporary list as the Expedition list
            this.setExpeditions(tempExpeditionList);
        }
    }

    //Runs events from the expedition
    public void runEvents()
    {

    }


    //Calculates the rewards for an expedition based on the perception requirement for the Expedition and the total perception of the
    //Adventuring party
    public void calculateRewards(int TotalPerception) {

    }

    //Runs the Current Expedition that was choosen by the player
    public void startExpedition(ArrayList<Adventurer> Party) {
        //a total for the amount of gold found on the Expedition
        int ExpeditionGoldRewards = 0;
        //ArrayList<Item> ItemRewards;
    }


}