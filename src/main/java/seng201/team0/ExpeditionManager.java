package seng201.team0;

import java.util.ArrayList;
import java.util.Random;


public class ExpeditionManager
{
    protected final int BASE_PERCEPTION_SCORE = 60;
    protected  final int BASE_STAMINA_SCORE = 250;
    protected  final int BASE_EXPEDITION_ARRAY_SIZE = 3;
    private ArrayList<Expedition> Expeditions;

    //The current Expedition the player has choosen and is working on
    private Expedition currentExpedition;

    private int numberOfExpeditions = 0;

    //Constructor for ExpeditionManager for if hard coding Expedition array is required, should use generateExpeditions
    public ExpeditionManager(ArrayList<Expedition> Expeditions)
    {
        this.Expeditions = Expeditions;
    }


    //Getter methods
    public ArrayList<Expedition> getExpeditions()
    {
        return Expeditions;
    }

    public Expedition getCurrentExpedition()
    {
        return currentExpedition;
    }

    public int getNumberOfExpeditions()
    {
        return numberOfExpeditions;
    }
    //Setter Methods
    public void setExpeditions(ArrayList<Expedition> Expeditions)
    {
        this.Expeditions = Expeditions;
    }

    public void setCurrentExpedition(Expedition currentExpedition)
    {
        this.currentExpedition = currentExpedition;
    }

    public void setNumberOfExpeditions(int numberOfExpeditions)
    {
        this.numberOfExpeditions = numberOfExpeditions;
    }

    //Generates Random Expeditions for the Player to choose
    public void generatePossibleExpeditions()
    {
        //Generate random Numbers for the expedition stats
        Random r = new Random();

        //Counter for number of Expeditions put into the array

        if(this.getCurrentExpedition() == null)
        {
            //Temporary list for the Expeditionss
            ArrayList<Expedition> tempExpeditionList = null;
            int i = 0;
            while(tempExpeditionList.size() < BASE_EXPEDITION_ARRAY_SIZE)
            {
                i += 1;
                //Code for randomly generating Perception and Stamina requirements for Expeditions
                int addToPerception = (r.nextInt(10) + 1)* numberOfExpeditions;
                int addToStamina = (r.nextInt(10) + 1) * numberOfExpeditions;

                //Code required for generating names and descriptions, possibly from a pre generated text file
                String newName = "Name Test: " +  i;
                String newDescription = "Description Test: " + i;


                Expedition newExpedition = new Expedition(newName,
                        newDescription,
                        (addToStamina * 15) + BASE_STAMINA_SCORE,
                        (addToPerception * 5) + BASE_PERCEPTION_SCORE,
                        (addToPerception + addToStamina) * 2);
                //Adds the New Expedition to the temporay Expedition list
                tempExpeditionList.add(newExpedition);

            }
            //Sets the temopary list as the Expedition list
            this.setExpeditions(tempExpeditionList);
        }
    }
    //Runs events from the expedition
    public void runEvents()
    {

    }




    //Calculates the rewards for an expedition based on the perception requirement for the Expedition and the total perception of the
    //Adventuring party
    public void calculateRewards(int TotalPerception)
    {

    }
    //Runs the Current Expedition that was choosen by the player
    public void startExpedition(ArrayList<Adventurer> Party)
    {
        //a total for the amount of gold found on the Expedition
        int ExpeditionGoldRewards = 0;
        //ArrayList<Item> ItemRewards;
    }
}


