package seng201.team0;

public class Event {
    //Full text and description for the event including telling the player the plot and the decision they
    //have to make
    private String description;

    //The Name for the 2 options the player can make
    private String optionOne;
    private String optionTwo;

    // Base reward for the Event
    private double reward = 0.0;

    //possible Item reward for lucky players


    public Event(String description, String optionOne, String optionTwo, double reward)
    {
        this.description = description;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.reward = reward;


    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setOptionOne(String optionOne)
    {
        this.optionOne = optionOne;
    }

    public void setOptionTwo(String optionTwo)
    {
        this.optionTwo = optionTwo;
    }

    public void setReward(double reward)
    {
        this.reward = reward;
    }




    public String getDescription()
    {
        return description;
    }

    public String getOptionOne()
    {
        return optionOne;
    }

    public String getOptionTwo()
    {
        return optionTwo;
    }

    public double getReward()
    {
        return reward;
    }



    //risky option, way to get rewards but also can lose stat points or extra events
    public void chooseOptionOne(int TotalPerception, int perceptionRequired  )
    {
        //chance for a reward
        int goodPercentage = 50;

        
        //

    }

    public void chooseOptionTwo(ExpeditionManager expeditionManager)
    {
        //run away
        //Takes away an extra event if the number of events is greater than 0
        if (expeditionManager.getNumberOfExpeditions() > 0 )
        {
            expeditionManager.setNumberOfExpeditions(expeditionManager.getNumberOfExpeditions() - 1);
        }

    }

    public String successMessage()
    {
        return "You Win";
    }

    public String failMessage()
    {
        return "You Lost";
    }


}
