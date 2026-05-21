package seng201.team0.adventurer;

public class Marksman extends Adventurer {
    public Marksman(String name) {
        super(name, Role.MARKSMAN, 85, 100, 45, 120);
    }

    @Override
    public void specialSkill() {
        perception += 20;
        System.out.println(name + " used Marksman special attack! +20 perception");
    }
}
