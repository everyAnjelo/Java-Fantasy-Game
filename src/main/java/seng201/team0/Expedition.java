package seng201.team0;

public class Expedition {
    private String Name;
    private String Description;
    private int staminaRequired;
    private int perceptionRequired;
    private double Reward;

//    Constructor for the Expeditions
    public void Expedition(String Name,String Description, int Stamina, int Perception, double Reward )
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









}
