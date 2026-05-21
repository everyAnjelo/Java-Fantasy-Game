package seng201.team0.unittests.services;
import org.junit.jupiter.api.Test;
import seng201.team0.expedition.EventType;
import seng201.team0.expedition.Expedition;


import static org.junit.jupiter.api.Assertions.*;


public class ExpeditionTest {

    EventType newEventType = EventType.SWAMP;
    Expedition expedition = new Expedition("TestExpedition",
            "This is the Test Expedition",
            100,
            50,
            50.0,
            newEventType);


    @Test
    public void testExpedition()
    {
        assertEquals(5, expedition.generateNumberOfEvents(500));
        assertEquals(2, expedition.generateNumberOfEvents(40));


    }


}
