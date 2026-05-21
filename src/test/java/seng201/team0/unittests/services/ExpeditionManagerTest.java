package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.adventurer.Adventurer;
import seng201.team0.adventurer.AdventurerManager;
import seng201.team0.expedition.EventType;
import seng201.team0.expedition.ExpeditionManager;
import seng201.team0.gameEnvironment.GameEnvironment;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpeditionManagerTest {


    private GameEnvironment gameEnvironment ;
    private ExpeditionManager expeditionManager ;
    private ArrayList<Adventurer> adventurers = new ArrayList<>();

    @BeforeEach
    public void startUpTest()
    {
        AdventurerManager adventurerManager = new AdventurerManager();
        this.gameEnvironment = new GameEnvironment();
        this.expeditionManager = new ExpeditionManager(gameEnvironment);


        Adventurer adventurer1 = adventurerManager.generateNewAdventurer(0);
        Adventurer adventurer2 = adventurerManager.generateNewAdventurer(0);

        adventurer1.setPerception(1000);
        adventurer2.setPerception(1000);

        adventurer1.setStamina(1000);
        adventurer2.setStamina(1000);

        adventurers.add(adventurer1);
        adventurers.add(adventurer2);

        expeditionManager.createAllEvents();
        expeditionManager.createNamesAndDescriptions();
        expeditionManager.generatePossibleExpeditions();




    }


    @Test
    public void testEventAndExpeditionGeneration()
    {
//        for(Expedition expedition : expeditionManager.getExpeditions())
//        {
//            System.out.println(expedition.getEventType().toString());
//        }
        assertEquals(EventType.SWAMP, expeditionManager.getExpeditions().get(0).getEventType());
        assertEquals(EventType.MOUNTAIN, expeditionManager.getExpeditions().get(1).getEventType());
        assertEquals(EventType.FOREST, expeditionManager.getExpeditions().get(2).getEventType());

    }

    @Test
    public void testStartExpedition()
    {
        expeditionManager.setCurrentExpedition(expeditionManager.getExpeditions().getFirst());

        expeditionManager.startExpedition(adventurers);

        assertEquals(5, expeditionManager.getNumberOfEvents());



    }


}
