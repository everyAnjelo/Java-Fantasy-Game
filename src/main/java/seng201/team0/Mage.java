package seng201.team0;

public class Mage extends Adventurer{
    public Mage(String name) {
        super(name, Role.MAGE, 70, 90, 35, 130);
    }

    @Override
    public void specialSkill() {
        stamina += 5;
        perception += 15;
        System.out.println(name + " used Mage special attack! +5 stamina, +15 perception");
    }
}
