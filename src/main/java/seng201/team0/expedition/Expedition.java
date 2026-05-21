package seng201.team0.expedition;

public class Expedition {
    /**
     * Constants for Class
     */
    protected final int BASE_EVENT_TOTAL = 5;

    private String name;
    private String description;
    private int staminaRequired;
    private int perceptionRequired;
    private double reward;
    private EventType eventType;

    /**
     * Constructor for the Expeditions
     * Expeditions represent the adventures the party goes on, if a party has the required stamina there will
     * be a maximum of 5 events per expedition
     *
     * @param Name the Name of the Expedition in String
     * @param Description A short description of the Expedition
     * @param Stamina an int representing the required total stamina of the party to get the maximum events for the expedition
     * @param Perception an int representing the minimum total perception of the party to get an average chance at rewards from events
     * @param Reward a double representing the base amount of gold the party gets for completing an event
     * @param eventType an Enum for determining the types of the events for this expedition
     */
    public Expedition(String Name, String Description, int Stamina, int Perception, double Reward, EventType eventType)
    {
        this.name = Name;
        this.description = Description;
        this.staminaRequired = Stamina;
        this.perceptionRequired = Perception;
        this.reward = Reward;
        this.eventType = eventType;


    }

    /* Getter Methods for Expeditions */

    /**
     * Getter method of the name of the Expedition
     */
    public String getName()

    {
        return name;
    }

    /**
     * Getter method for the description of the Expedition
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter method for the Stamina requirement for the Expedition
     */
    public int getStaminaRequired()
    {
        return staminaRequired;
    }

    /**
     * Getter method for the Perception requirement for the Expedition
     */
    public int getPerceptionRequired()
    {
        return perceptionRequired;
    }

    /**
     * Getter method for the Reward of the Expedition
     */
    public double getReward()
    {
        return reward;
    }

    /**
     * Getter method of the event type of the Expedition
     */
    public EventType getEventType()
    {
        return eventType;
    }

    /* Setter Methods for Expeditions */

    /**
     * Setter method for the Name of the Expedition
     */
    public void setName(String Name)
    {
        this.name = Name;
    }

    /**
     * Setter method for the Description of the Expedition
     */
    public void setDescription(String Description)
    {
        this.description = Description;
    }

    /**
     * Setter method for the Stamina Requirement for the Expedition
     */
    public void setStaminaRequired(int Stamina)
    {
        this.staminaRequired = Stamina;
    }

    /**
     * Setter method for the Perception Requirement for the Expedition
     */
    public void setPerceptionRequired(int Perception)
    {
        this.perceptionRequired = Perception;
    }

    /**
     * Setter method for the Reward of the Expedition
     */
    public void setReward(double Reward)
    {
        this.reward = Reward;
    }

    /**
     * Setter method for the Event type for the Expedition
     */
    public void setEventType(EventType eventType)
    {
        this.eventType = eventType;
    }

    /**
     * Calculates the number of Events for the Expedition based on stamina
     */
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







}
