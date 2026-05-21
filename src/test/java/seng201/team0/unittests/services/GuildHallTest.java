package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.adventurer.Adventurer;
import seng201.team0.guildHall.GuildHall;
import seng201.team0.adventurer.Mage;
import seng201.team0.adventurer.Marksman;
import seng201.team0.adventurer.Rogue;
import seng201.team0.adventurer.Warrior;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the GuildHall implementation.
 */
class GuildHallTest {

    private GuildHall testGuildHall;

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void setupTest() {
        testGuildHall = new GuildHall();
    }

    /**
     * Test that the guild hall starts with four adventurers.
     */
    @Test
    void testConstructor() {
        assertEquals(4, testGuildHall.getAvailableAdventurers().size());
    }

    /**
     * Test that starting adventurers are generated correctly.
     */
    @Test
    void testGenerateStartingAdventurers() {
        testGuildHall.getAvailableAdventurers().clear();

        assertEquals(0, testGuildHall.getAvailableAdventurers().size());

        testGuildHall.generateStartingAdventurers();

        assertEquals(4, testGuildHall.getAvailableAdventurers().size());
        assertTrue(testGuildHall.getAvailableAdventurers().get(0) instanceof Warrior);
        assertTrue(testGuildHall.getAvailableAdventurers().get(1) instanceof Mage);
        assertTrue(testGuildHall.getAvailableAdventurers().get(2) instanceof Marksman);
        assertTrue(testGuildHall.getAvailableAdventurers().get(3) instanceof Rogue);
    }

    /**
     * Test creating a warrior from choice 1.
     */
    @Test
    void testCreateWarrior() {
        Adventurer adventurer = testGuildHall.createAdventurer(1, "Arthur");

        assertTrue(adventurer instanceof Warrior);
        assertEquals("Arthur", adventurer.getName());
    }

    /**
     * Test creating a mage from choice 2.
     */
    @Test
    void testCreateMage() {
        Adventurer adventurer = testGuildHall.createAdventurer(2, "Merlin");

        assertTrue(adventurer instanceof Mage);
        assertEquals("Merlin", adventurer.getName());
    }

    /**
     * Test creating a marksman from choice 3.
     */
    @Test
    void testCreateMarksman() {
        Adventurer adventurer = testGuildHall.createAdventurer(3, "Robin");

        assertTrue(adventurer instanceof Marksman);
        assertEquals("Robin", adventurer.getName());
    }

    /**
     * Test creating a rogue from choice 4.
     */
    @Test
    void testCreateRogue() {
        Adventurer adventurer = testGuildHall.createAdventurer(4, "Shadow");

        assertTrue(adventurer instanceof Rogue);
        assertEquals("Shadow", adventurer.getName());
    }

    /**
     * Test that invalid choice returns null.
     */
    @Test
    void testCreateAdventurerInvalidChoice() {
        Adventurer adventurer = testGuildHall.createAdventurer(5, "Invalid");

        assertNull(adventurer);
    }

    /**
     * Test that blank name becomes Adventurer.
     */
    @Test
    void testCreateAdventurerBlankName() {
        Adventurer adventurer = testGuildHall.createAdventurer(1, "");

        assertTrue(adventurer instanceof Warrior);
        assertEquals("Adventurer", adventurer.getName());
    }

    /**
     * Test that null name becomes Adventurer.
     */
    @Test
    void testCreateAdventurerNullName() {
        Adventurer adventurer = testGuildHall.createAdventurer(1, null);

        assertTrue(adventurer instanceof Warrior);
        assertEquals("Adventurer", adventurer.getName());
    }

    /**
     * Test that refreshAdventurers brings the list back to four adventurers.
     */
    @Test
    void testRefreshAdventurers() {
        testGuildHall.getAvailableAdventurers().clear();

        assertEquals(0, testGuildHall.getAvailableAdventurers().size());

    }

    /**
     * Test replacing adventurers after an expedition.
     */
    @Test
    void testReplaceAdventurersAfterExpedition() {
        testGuildHall.replaceAdventurersAfterExpedition(1);

        assertEquals(4, testGuildHall.getAvailableAdventurers().size());
        assertTrue(testGuildHall.getAvailableAdventurers().get(0) instanceof Warrior);
        assertTrue(testGuildHall.getAvailableAdventurers().get(1) instanceof Mage);
        assertTrue(testGuildHall.getAvailableAdventurers().get(2) instanceof Marksman);
        assertTrue(testGuildHall.getAvailableAdventurers().get(3) instanceof Rogue);
    }

    /**
     * Test that upgradeAdventurer increases stamina and perception.
     */
    @Test
    void testUpgradeAdventurerIncreasesStats() {
        Adventurer adventurer = new Warrior("Arthur");

        int startingStamina = adventurer.getStamina();
        int startingPerception = adventurer.getPerception();

        testGuildHall.upgradeAdventurer(adventurer, 1);

        assertEquals((int) (startingStamina * 1.10), adventurer.getStamina());
        assertEquals((int) (startingPerception * 1.10), adventurer.getPerception());
    }

    /**
     * Test that upgradeAdventurer lowers pay and hiring cost.
     */
    @Test
    void testUpgradeAdventurerLowersCosts() {
        Adventurer adventurer = new Warrior("Arthur");

        double startingPay = adventurer.getPay();
        double startingHiringCost = adventurer.getHiringCost();

        testGuildHall.upgradeAdventurer(adventurer, 1);

        assertEquals(startingPay * 0.90, adventurer.getPay());
        assertEquals(startingHiringCost * 0.90, adventurer.getHiringCost());
    }

    /**
     * Test that cost multiplier does not go below 0.5.
     */
    @Test
    void testUpgradeAdventurerCostMinimum() {
        Adventurer adventurer = new Warrior("Arthur");

        double startingPay = adventurer.getPay();
        double startingHiringCost = adventurer.getHiringCost();

        testGuildHall.upgradeAdventurer(adventurer, 10);

        assertEquals(startingPay * 0.5, adventurer.getPay());
        assertEquals(startingHiringCost * 0.5, adventurer.getHiringCost());
    }

    /**
     * Test getting cheapest-hiring cost.
     */
    @Test
    void testGetCheapestHiringCost() {
        double cheapest = testGuildHall.getCheapestHiringCost();

        assertEquals(100.0, cheapest);
    }

    /**
     * Test cheapest hiring cost when there are no adventurers.
     */
    @Test
    void testGetCheapestHiringCostWhenEmpty() {
        testGuildHall.getAvailableAdventurers().clear();

        assertEquals(0.0, testGuildHall.getCheapestHiringCost());
    }
}