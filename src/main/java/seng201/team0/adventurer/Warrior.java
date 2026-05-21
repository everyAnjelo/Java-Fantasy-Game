package seng201.team0.adventurer;

public class Warrior extends Adventurer {

    public Warrior(String name) {
        super(name, Role.WARRIOR, 100, 40, 45, 110);
    }

    @Override
    public void specialSkill() {
        stamina += 15;
        System.out.println(name + " used Warrior special attack! +15 stamina");
    }
}
