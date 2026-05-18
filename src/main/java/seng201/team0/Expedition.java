package seng201.team0;

public class Expedition {
    //Constants for Class
    protected final int BASE_EVENT_TOTAL = 5;

    private String name;
    private String description;
    private int staminaRequired;
    private int perceptionRequired;
    private double reward;
    private EventType eventType;

    //    Constructor for the Expeditions
    public Expedition(String Name, String Description, int Stamina, int Perception, double Reward, EventType eventType)
    {
        this.name = Name;
        this.description = Description;
        this.staminaRequired = Stamina;
        this.perceptionRequired = Perception;
        this.reward = Reward;
        this.eventType = eventType;


    }

    //Getter Methods for Expeditions
    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
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
        return reward;
    }

    //Setter Methods for Expeditions

    public void setName(String Name)
    {
        this.name = Name;
    }

    public void setDescription(String Description)
    {
        this.description = Description;
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
        this.reward = Reward;
    }

    //Calculates the number of Events for the Expedition based on stamina
    public int generateNumberOfEvents(int TotalStamina)
    {
        //Int for dividing the EventTotal, Creating a new Event total if TotalStamina is too low
        int EventDivder = 0;

        //Variable for the Total amount of events for the expedition, based on BASE_EVENT_TOTAL as a starting point
        int EventTotal = BASE_EVENT_TOTAL;

        if (TotalStamina >= this.staminaRequired)
        {
            return EventTotal;
        }
        else
        {
            //Calculation for Number of events
            EventDivder = this.staminaRequired / EventTotal;



            return (TotalStamina / EventDivder) ;


        }


    }




    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
