package seng201.team0.unittests.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.adventurer.Adventurer;
import seng201.team0.adventurer.Role;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Adventurer implementation.
 */
class AdventurerTest {

    private TestAdventurer testAdventurer;

    /**
     * Since Adventurer is abstract, we create a simple test version of it.
     */
    static class TestAdventurer extends Adventurer {

        private boolean specialSkillUsed;

        public TestAdventurer() {
            super("Arthur", Role.WARRIOR, 100, 50, 20.0, 100.0);
            specialSkillUsed = false;
        }

        @Override
        public void specialSkill() {
            specialSkillUsed = true;
        }

        public boolean getSpecialSkillUsed() {
            return specialSkillUsed;
        }
    }

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void setupTest() {
        testAdventurer = new TestAdventurer();
    }

    /**
     * Test that the constructor sets the starting values correctly.
     */
    @Test
    void testConstructor() {
        assertEquals("Arthur", testAdventurer.getName());
        assertEquals(Role.WARRIOR, testAdventurer.getRole());
        assertEquals(100, testAdventurer.getStamina());
        assertEquals(50, testAdventurer.getPerception());
        assertEquals(20.0, testAdventurer.getPay());
        assertEquals(100.0, testAdventurer.getHiringCost());
        assertEquals(0, testAdventurer.getExpeditionCount());
        assertEquals(5, testAdventurer.getMaxExpeditions());
        assertFalse(testAdventurer.isRetired());
        assertNull(testAdventurer.getEquippedWeapon());
    }

    /**
     * Test that an adventurer can go on an expedition at the start.
     */
    @Test
    void testCanGoOnExpeditionAtStart() {
        assertTrue(testAdventurer.canGoOnExpedition());
    }

    /**
     * Test that completing an expedition increases expedition count by one.
     */
    @Test
    void testCompleteExpedition() {
        assertEquals(0, testAdventurer.getExpeditionCount());

        testAdventurer.completeExpedition();

        assertEquals(1, testAdventurer.getExpeditionCount());
    }

    /**
     * Test that the special skill is used on the third expedition.
     */
    @Test
    void testSpecialSkillUsedOnThirdExpedition() {
        assertFalse(testAdventurer.getSpecialSkillUsed());

        testAdventurer.completeExpedition();
        testAdventurer.completeExpedition();
        testAdventurer.completeExpedition();

        assertEquals(3, testAdventurer.getExpeditionCount());
        assertTrue(testAdventurer.getSpecialSkillUsed());
    }

    /**
     * Test that retiring an adventurer makes them retired.
     */
    @Test
    void testRetire() {
        assertFalse(testAdventurer.isRetired());

        testAdventurer.retire();

        assertTrue(testAdventurer.isRetired());
    }

    /**
     * Test that a retired adventurer cannot go on an expedition.
     */
    @Test
    void testRetiredAdventurerCannotGoOnExpedition() {
        testAdventurer.retire();

        assertFalse(testAdventurer.canGoOnExpedition());
    }

    /**
     * Test that an adventurer cannot go on an expedition after reaching max expeditions.
     */
    @Test
    void testCannotGoOnExpeditionAtMaxExpeditions() {
        testAdventurer.setExpeditionCount(5);

        assertFalse(testAdventurer.canGoOnExpedition());
    }

    /**
     * Test adding stamina.
     */
    @Test
    void testAddStamina() {
        assertEquals(100, testAdventurer.getStamina());

        testAdventurer.addStamina(20);

        assertEquals(120, testAdventurer.getStamina());
    }

    /**
     * Test adding perception.
     */
    @Test
    void testAddPerception() {
        assertEquals(50, testAdventurer.getPerception());

        testAdventurer.addPerception(10);

        assertEquals(60, testAdventurer.getPerception());
    }

    /**
     * Test changing the adventurer name.
     */
    @Test
    void testSetName() {
        testAdventurer.setName("Lancelot");

        assertEquals("Lancelot", testAdventurer.getName());
    }

    /**
     * Test changing the adventurer stamina.
     */
    @Test
    void testSetStamina() {
        testAdventurer.setStamina(80);

        assertEquals(80, testAdventurer.getStamina());
    }

    /**
     * Test changing the adventurer perception.
     */
    @Test
    void testSetPerception() {
        testAdventurer.setPerception(70);

        assertEquals(70, testAdventurer.getPerception());
    }

    /**
     * Test changing the adventurer pay.
     */
    @Test
    void testSetPay() {
        testAdventurer.setPay(30.0);

        assertEquals(30.0, testAdventurer.getPay());
    }

    /**
     * Test changing the adventurer hiring cost.
     */
    @Test
    void testSetHiringCost() {
        testAdventurer.setHiringCost(150.0);

        assertEquals(150.0, testAdventurer.getHiringCost());
    }
}