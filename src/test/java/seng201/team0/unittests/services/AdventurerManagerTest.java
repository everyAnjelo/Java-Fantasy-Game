package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.adventurer.Adventurer;
import seng201.team0.adventurer.AdventurerManager;
import seng201.team0.adventurer.Role;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the AdventurerManager implementation.
 */
class AdventurerManagerTest {

    private AdventurerManager testManager;
    private TestAdventurer testAdventurer;
    private TestAdventurer secondAdventurer;

    /**
     * Simple adventurer class used only for testing.
     * This is needed because Adventurer is abstract.
     */
    static class TestAdventurer extends Adventurer {

        public TestAdventurer(String name, int stamina, int perception, double pay) {
            super(name, Role.WARRIOR, stamina, perception, pay, 100.0);
        }

        @Override
        public void specialSkill() {
            // No special skill needed for AdventurerManager tests.
        }
    }

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void setupTest() {
        testManager = new AdventurerManager();

        testAdventurer = new TestAdventurer("Arthur", 100, 50, 20.0);
        secondAdventurer = new TestAdventurer("Lancelot", 80, 40, 15.0);
    }

    /**
     * Test that the manager starts with empty teams.
     */
    @Test
    void testConstructor() {
        assertEquals(0, testManager.getMainTeam().size());
        assertEquals(0, testManager.getReserveTeam().size());
        assertEquals(5, testManager.getMaxMainTeam());
        assertEquals(5, testManager.getMaxReserveTeam());
    }

    /**
     * Test adding an adventurer to the main team.
     */
    @Test
    void testAddToMain() {
        testManager.addToMain(testAdventurer);

        assertEquals(1, testManager.getMainTeam().size());
        assertTrue(testManager.getMainTeam().contains(testAdventurer));
    }

    /**
     * Test that null is not added to the main team.
     */
    @Test
    void testAddNullToMain() {
        testManager.addToMain(null);

        assertEquals(0, testManager.getMainTeam().size());
    }

    /**
     * Test that the same adventurer is not added twice to the main team.
     */
    @Test
    void testCannotAddSameAdventurerToMainTwice() {
        testManager.addToMain(testAdventurer);
        testManager.addToMain(testAdventurer);

        assertEquals(1, testManager.getMainTeam().size());
    }

    /**
     * Test adding an adventurer to the reserve team.
     */
    @Test
    void testAddToReserve() {
        testManager.addToReserve(testAdventurer);

        assertEquals(1, testManager.getReserveTeam().size());
        assertTrue(testManager.getReserveTeam().contains(testAdventurer));
    }

    /**
     * Test that null is not added to the reserve team.
     */
    @Test
    void testAddNullToReserve() {
        testManager.addToReserve(null);

        assertEquals(0, testManager.getReserveTeam().size());
    }

    /**
     * Test that adding an adventurer to main removes them from reserve.
     */
    @Test
    void testMoveFromReserveToMain() {
        testManager.addToReserve(testAdventurer);

        assertEquals(1, testManager.getReserveTeam().size());

        testManager.addToMain(testAdventurer);

        assertEquals(1, testManager.getMainTeam().size());
        assertEquals(0, testManager.getReserveTeam().size());
        assertTrue(testManager.getMainTeam().contains(testAdventurer));
    }

    /**
     * Test that adding an adventurer to reserve removes them from main
     * when there is more than one adventurer in main.
     */
    @Test
    void testMoveFromMainToReserve() {
        testManager.addToMain(testAdventurer);
        testManager.addToMain(secondAdventurer);

        testManager.addToReserve(testAdventurer);

        assertEquals(1, testManager.getMainTeam().size());
        assertEquals(1, testManager.getReserveTeam().size());
        assertTrue(testManager.getReserveTeam().contains(testAdventurer));
        assertFalse(testManager.getMainTeam().contains(testAdventurer));
    }

    /**
     * Test that the last main adventurer cannot be moved to reserve.
     */
    @Test
    void testCannotMoveLastMainAdventurerToReserve() {
        testManager.addToMain(testAdventurer);

        testManager.addToReserve(testAdventurer);

        assertEquals(1, testManager.getMainTeam().size());
        assertEquals(0, testManager.getReserveTeam().size());
        assertTrue(testManager.getMainTeam().contains(testAdventurer));
    }


    /**
     * Test retiring an adventurer from reserve.
     */
    @Test
    void testRetireAdventurerFromReserve() {
        testManager.addToReserve(testAdventurer);

        testManager.retireAdventurer(testAdventurer);

        assertTrue(testAdventurer.isRetired());
        assertEquals(0, testManager.getReserveTeam().size());
    }

    /**
     * Test that the last main adventurer cannot be retired.
     */
    @Test
    void testCannotRetireLastMainAdventurer() {
        testManager.addToMain(testAdventurer);

        testManager.retireAdventurer(testAdventurer);

        assertFalse(testAdventurer.isRetired());
        assertEquals(1, testManager.getMainTeam().size());
    }

    /**
     * Test getting the total stamina of the main team.
     */
    @Test
    void testGetTotalStamina() {
        testManager.addToMain(testAdventurer);
        testManager.addToMain(secondAdventurer);

        assertEquals(180, testManager.getTotalStamina());
    }

    /**
     * Test getting the total perception of the main team.
     */
    @Test
    void testGetTotalPerception() {
        testManager.addToMain(testAdventurer);
        testManager.addToMain(secondAdventurer);

        assertEquals(90, testManager.getTotalPerception());
    }

    /**
     * Test getting the total pay of the main team.
     */
    @Test
    void testGetTotalPay() {
        testManager.addToMain(testAdventurer);
        testManager.addToMain(secondAdventurer);

        assertEquals(35.0, testManager.getTotalPay());
    }

    /**
     * Test that retired adventurers are not counted in total stamina.
     */
    @Test
    void testRetiredAdventurerNotCountedInTotalStamina() {
        testManager.addToMain(testAdventurer);
        testManager.addToMain(secondAdventurer);

        testAdventurer.retire();

        assertEquals(80, testManager.getTotalStamina());
    }

    /**
     * Test getting available adventurers from main and reserve.
     */
    @Test
    void testGetAvailableAdventurers() {
        testManager.addToMain(testAdventurer);
        testManager.addToReserve(secondAdventurer);

        assertEquals(2, testManager.getAvailableAdventurers().size());
        assertTrue(testManager.getAvailableAdventurers().contains(testAdventurer));
        assertTrue(testManager.getAvailableAdventurers().contains(secondAdventurer));
    }

    /**
     * Test that retired adventurers are not available.
     */
    @Test
    void testRetiredAdventurerIsNotAvailable() {
        testManager.addToMain(testAdventurer);
        testAdventurer.retire();

        assertEquals(0, testManager.getAvailableAdventurers().size());
    }

    /**
     * Test completing an expedition for the main team.
     */
    @Test
    void testCompleteExpeditionForTeam() {
        testManager.addToMain(testAdventurer);
        testManager.addToMain(secondAdventurer);

        testManager.completeExpeditionForTeam();

        assertEquals(1, testAdventurer.getExpeditionCount());
        assertEquals(1, secondAdventurer.getExpeditionCount());
    }
}