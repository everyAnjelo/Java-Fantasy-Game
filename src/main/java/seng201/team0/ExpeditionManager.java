package seng201.team0;

import java.util.ArrayList;


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
        //Counter for number of Expeditions put into the array
        int i = 0;
        if(this.getCurrentExpedition() == null)
        {
            while( i < this.Expeditions.size())
            {

            }
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


