package seng201.team0.adventurer;

public class Rogue  extends Adventurer {
    public Rogue(String name) {
        super(name, Role.ROGUE, 80, 85, 40, 110);
    }

    @Override
    public void specialSkill() {
        stamina += 5;
        perception += 10;
        System.out.println(name + " used Rogue special attack! +5 stamina, +10 perception");
    }
}
