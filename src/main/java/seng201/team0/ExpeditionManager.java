package seng201.team0;

import java.util.ArrayList;

public class ExpeditionManager
{
    private ArrayList<Expedition> Expeditions;

    //The current Expedition the player has choosen and is working on
    private Expedition currentExpedition;

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
    //Setter Methods
    public void setExpeditions(ArrayList<Expedition> Expeditions)
    {
        this.Expeditions = Expeditions;
    }

    public void setCurrentExpedition(Expedition currentExpedition)
    {
        this.currentExpedition = currentExpedition;
    }


    //Runs the Current Expedition that was choosen by the player
    public void startExpedition(ArrayList<Adventurer> Party)
    {

    }
}


