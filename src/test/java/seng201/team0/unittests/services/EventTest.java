package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import seng201.team0.adventurer.Adventurer;
import seng201.team0.adventurer.AdventurerManager;
import seng201.team0.expedition.Event;
import seng201.team0.expedition.ExpeditionManager;
import seng201.team0.gameEnvironment.GameEnvironment;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private Event testEvent = new Event(
            "This is a Test case for the Description of the Event Class",
            "Attack or Enagage the Enemy Test Case",
            "Run Away or escape the problem Test Case",
            20.0);
    private GameEnvironment gameEnvironment ;
    private ExpeditionManager expeditionManager ;

    private ArrayList<Adventurer> adventurers = new ArrayList<>();







    /**
     * Setup before each test.
     */
    @BeforeEach
    public void startUpTest()
    {
        AdventurerManager adventurerManager = new AdventurerManager();
        this.gameEnvironment = new GameEnvironment();
        this.expeditionManager = new ExpeditionManager(gameEnvironment);
        gameEnvironment.gold = 10;

        expeditionManager.setNumberOfEvents(5);
        gameEnvironment.totalExpeditions = 5;
        //expeditionManager.setNumberOfExpeditions(5);
        Adventurer adventurer1 = adventurerManager.generateNewAdventurer(0);
        Adventurer adventurer2 = adventurerManager.generateNewAdventurer(0);

        adventurer1.setPerception(50);
        adventurer2.setPerception(40);

        adventurer1.setStamina(100);
        adventurer2.setStamina(80);

        adventurers.add(adventurer1);
        adventurers.add(adventurer2);

        testEvent.setExpeditionManager(expeditionManager);
        testEvent.setParty(adventurers);


    }

    @Test
    public void testChooseOptionOne()
    {

        testEvent.setPartyPerception(500);
        testEvent.setPerceptionRequired(100);


        testEvent.chooseOptionOne();

        assertEquals(105, adventurers.getFirst().getStamina());
        assertEquals(55, adventurers.getFirst().getPerception());

        assertEquals(85, adventurers.get(1).getStamina());
        assertEquals(45, adventurers.get(1).getPerception());

        assertEquals(40.0, expeditionManager.getTotalingEarnings());



    }



}

