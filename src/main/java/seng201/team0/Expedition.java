package seng201.team0;

public class Expedition {
    //Constants for Class
    protected final int BASE_EVENT_TOTAL = 5;

    private String Name;
    private String Description;
    private int staminaRequired;
    private int perceptionRequired;
    private double Reward;

//    Constructor for the Expeditions
    public Expedition(String Name,String Description, int Stamina, int Perception, double Reward )
    {
        this.Name = Name;
        this.Description = Description;
        this.staminaRequired = Stamina;
        this.perceptionRequired = Perception;
        this.Reward = Reward;


    }

    //Getter Methods for Expeditions
    public String getName()
    {
        return Name;
    }

    public String getDescription()
    {
        return Description;
    }
    public int getStaminaRequired()
    {
        return staminaRequired;
    }
    public int getPerceptionRequired()
    {
        return perceptionRequired;
    }
    public double getReward()
    {
        return Reward;
    }

    //Setter Methods for Expeditions

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public void setDescription(String Description)
    {
        this.Description = Description;
    }

    public void setStaminaRequired(int Stamina)
    {
        this.staminaRequired = Stamina;
    }

    public void setPerceptionRequired(int Perception)
    {
        this.perceptionRequired = Perception;
    }

    public void setReward(double Reward)
    {
        this.Reward = Reward;
    }

    //Calculates the number of Events for the Expedition based on stamina
    public int numberOfEvents(int TotalStamina)
    {
        //Int for divdeing the EventTotal, Creating a new Event total if TotalStamina is too low
        int EventDivder = 0;

        //Varaible for the Total amount of events for the expedition, based on BASE_EVENT_TOTAL as a starting point
        int EventTotal = BASE_EVENT_TOTAL;

        if (TotalStamina >= this.staminaRequired)
        {
            return EventTotal;
        }
        else
        {
            //Calculation for Number of events
            EventDivder = this.staminaRequired / EventTotal;


            //Returning the new event total which should be no lower than 1
            return  TotalStamina / EventDivder ;


        }


    }






}
