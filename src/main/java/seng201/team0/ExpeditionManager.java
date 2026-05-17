package seng201.team0;

import java.util.ArrayList;
import java.util.Random;


public class ExpeditionManager {
    protected final int BASE_PERCEPTION_SCORE = 60;
    protected final int BASE_STAMINA_SCORE = 250;
    protected final int BASE_EXPEDITION_ARRAY_SIZE = 3;
    private ArrayList<Expedition> Expeditions;
    private GameEnvironment gameenvironment;
    //The current Expedition the player has chosen and is working on
    private Expedition currentExpedition;
    //Current Event
    private Event currentEvent;

    private double totalingEarnings;

    private ArrayList<String> expeditionNames;
    private ArrayList<String> expeditionDescriptions;
    private ArrayList<Event> swampEvents;
    private ArrayList<Event> forestEvents;
    private ArrayList<Event> mountainEvents;
    private int numberOfExpeditions = 0;

    //Constructor for ExpeditionManager for if hard coding Expedition array is required, should use generateExpeditions
    public ExpeditionManager(ArrayList<Expedition> Expeditions, GameEnvironment gameEnvironment) {
        this.Expeditions = Expeditions;
        this.gameenvironment = gameEnvironment;
    }


    //Getter methods
    public ArrayList<Expedition> getExpeditions() {
        return Expeditions;
    }

    public Expedition getCurrentExpedition() {
        return currentExpedition;
    }

    public int getNumberOfExpeditions() {
        return numberOfExpeditions;
    }

    //Setter Methods
    public void setExpeditions(ArrayList<Expedition> Expeditions) {
        this.Expeditions = Expeditions;
    }

    public void setCurrentExpedition(Expedition currentExpedition) {
        this.currentExpedition = currentExpedition;
    }

    public void setNumberOfExpeditions(int numberOfExpeditions) {
        this.numberOfExpeditions = numberOfExpeditions;
    }

    public void setExpeditionNames(ArrayList<String> expeditionNames) {
        this.expeditionNames = expeditionNames;
    }

    public void setExpeditionDescriptions(ArrayList<String> expeditionDescriptions) {
        this.expeditionDescriptions = expeditionDescriptions;
    }
    public void createAllEvents()
    {
        ArrayList<Event> tempSwampEvents = new ArrayList<>();
        tempSwampEvents.add(new Event("The Sinking Lantern\n" +
                " A faint lantern flickers in the fog, hovering above a patch of bubbling black mud. A weak voice calls for help.",
                "Investigate the lantern and attempt a rescue. You may uncover a lost traveler carrying rare supplies—or awaken a swamp wraith.",
                "Ignore the cries and take a longer route around the bog, avoiding danger but losing valuable time.",
                20.0));
        tempSwampEvents.add(new Event("Rotfang Ambush\n" +
                " A pack of crocodile-like beasts with skeletal armor bursts from the murky water.",
                "Engage: Fight the Rotfangs for meat, hides, and possible treasure hidden in their nest.",
                "Escape: Climb onto twisted mangrove roots and flee through the reeds before they surround you.",
                10
                ));
        tempSwampEvents.add(new Event("The Poison Bloom\n" +
                " A massive glowing flower opens nearby, releasing shimmering spores into the air.",
                "Engage: Harvest the spores for alchemy ingredients while resisting hallucinations caused by the bloom.\n",
                "Escape: Cover your face and retreat before the toxic cloud spreads.",
                5));
        tempSwampEvents.add(new Event("Whispers Beneath the Water\n" +
                " Strange whispers echo from beneath the swamp surface, promising forgotten secrets and riches.",
                "Engage: Follow the voices into the deeper marsh to search for hidden ruins.",
                "Escape: Block out the whispers and move on before madness takes hold.",
                30));
        tempSwampEvents.add(new Event("The Bone Ferry\n" +
                " An old ferryman made entirely of bones offers passage across dangerous waters—for a price.",
                "Engage: Bargain with the ferryman and cross safely, though the payment may be unsettling.",
                "Escape: Refuse the offer and attempt to cross the swamp on foot through hazardous terrain.",
                50));

        ArrayList<Event> tempForestEvents = new ArrayList<>();
        tempForestEvents.add(new Event("The Sleeping Treant\n" +
                " A colossal tree creature slumbers across the path, glowing runes pulsing across its bark.",
                "Engage: Attempt to communicate with or awaken the Treant to gain guidance or blessings.",
                "Escape: Quietly sneak around it before it stirs.",
                30));
        tempForestEvents.add(new Event("Fairy Ring Gathering\n" +
                " Tiny glowing fae dance in a circle of mushrooms, singing in an unknown language.",
                "Engage: Join the dance and accept the fae’s challenge for magical rewards.",
                "Escape: Avoid eye contact and leave before the fae notice your presence.",
                10));
        tempSwampEvents.add(new Event("The Mirror Stag\n" +
                " A silver stag steps onto the trail, its antlers reflecting visions of the future.",
                "Engage: Follow the stag deeper into the forest to seek prophecy or hidden treasure.",
                "Escape: Turn away and continue your journey without risking enchantment.",
                40));
        tempSwampEvents.add(new Event("Cursed Vines\n" +
                " Thick vines suddenly lash out from nearby trees and attempt to entangle travelers.",
                "Engage: Cut through the vines to discover what they are protecting.",
                "Escape: Retreat quickly before the vines completely block the trail.",
                5));

        tempSwampEvents.add(new Event("Moonlit Shrine\n" +
                " An ancient shrine glows beneath the moonlight, surrounded by floating blue spirits.",
                "Engage: Offer a tribute at the shrine for a possible blessing or magical artifact.",
                "Escape: Leave the shrine undisturbed to avoid angering the spirits.",
                20));


        ArrayList<Event> tempMountainEvents = new ArrayList<>();

        tempMountainEvents.add(new Event("Rockslide Warning\n" +
                " Pebbles begin tumbling down the cliffs as the mountain rumbles overhead.",
                "Engage: Climb upward to stabilize the path and search for the source of the disturbance.",
                "Escape: Sprint downhill before the full rockslide crashes across the trail.",
                5));
        tempMountainEvents.add(new Event("The Frost Hermit\n" +
                " A hooded hermit sits beside a frozen campfire, offering cryptic advice to travelers.",
                "Engage: Speak with the hermit to learn secrets about the mountain or receive a powerful charm.",
                "Escape: Politely decline and continue before the weather worsens.",
                10
                ));
        tempMountainEvents.add(new Event("Wyvern Shadow\n" +
                " A massive winged shadow circles overhead, letting out a piercing screech.",
                "Engage: Track the wyvern to its nest for treasure or glory.",
                "Escape: Hide beneath rocky overhangs until the creature flies away.",
                40));
        tempMountainEvents.add(new Event("The Broken Bridge\n" +
                " A rope bridge crossing a deep ravine hangs by only a few strands.",
                "Engage: Carefully repair or cross the bridge to reach a hidden shortcut.",
                "Escape: Take the safer mountain trail, adding hours to your journey.",
                10));
        tempMountainEvents.add(new Event("Frozen Traveler\n" +
                " A nearly frozen adventurer collapses on the roadside, clutching a mysterious map.",
                "Engage: Help the traveler recover and learn the location of a hidden mountain vault.",
                "Escape: Leave immediately before the freezing storm catches up to you.",
                50));


        setSwampEvents(tempSwampEvents);
        setMountainEvents(tempMountainEvents);
        setForestEvents(tempForestEvents);

    }

    public void createNamesAndDescriptions()
    {
        ArrayList<String> tempDescriptions = new ArrayList<String>();
        tempDescriptions.add("A large rotting swamp, filled with stagnant water and the stench of death");
        tempDescriptions.add("A lush forest, trees as far as the eye can see, filled with wildlife");
        tempDescriptions.add("A mountain path filled with rocks and debris, stretching into the horizon");

        ArrayList<String> tempNames = new ArrayList<String>();
        tempNames.add("Death Swamp");
        tempNames.add("Mountain Path");
        tempNames.add("Mystical Forest");

        setExpeditionDescriptions(tempDescriptions);
        setExpeditionNames(tempNames);
    }

    //Generates Random Expeditions for the Player to choose
    public void generatePossibleExpeditions()
    {
        //set the names and descriptions for the expeditions
        createNamesAndDescriptions();

        //Generate random Numbers for the expedition stats
        Random r = new Random();

        //Counter for number of Expeditions put into the array

        if (this.getCurrentExpedition() == null) {
            //Temporary list for the Expeditions
            ArrayList<Expedition> tempExpeditionList = new ArrayList<>();
            int i = 0;
            while (tempExpeditionList.size() < BASE_EXPEDITION_ARRAY_SIZE) {
                i += 1;
                //Code for randomly generating Perception and Stamina requirements for Expeditions
                int addToPerception = (r.nextInt(10) + 1) * numberOfExpeditions;
                int addToStamina = (r.nextInt(10) + 1) * numberOfExpeditions;

                //Code required for generating names and descriptions, possibly from a pre generated text file
                int randomIndexForStrings = r.nextInt(1,4);

                String newName = expeditionNames.get(randomIndexForStrings);
                String newDescription = expeditionDescriptions.get(randomIndexForStrings);
                EventType newEventType = switch (randomIndexForStrings) {
                    case 1 -> EventType.SWAMP;
                    case 2 -> EventType.MOUNTAIN;
                    case 3 -> EventType.FOREST;
                    default -> null;
                };

                Expedition newExpedition = new Expedition(
                        newName,
                        newDescription,
                        (addToStamina * 15) + BASE_STAMINA_SCORE,
                        (addToPerception * 5) + BASE_PERCEPTION_SCORE,
                        (addToPerception + addToStamina) * 2,
                        newEventType);

                //Adds the New Expedition to the temporary Expedition list
                tempExpeditionList.add(newExpedition);

            }
            //Sets the temporary list as the Expedition list
            this.setExpeditions(tempExpeditionList);
        }
    }

    public void chooseRandomEvent(EventType type)
    {

    }

    //Runs the Current Expedition that was choosen by the player
    public void startExpedition(ArrayList<Adventurer> Party) {
        //a total for the amount of gold found on the Expedition
        int ExpeditionGoldRewards = 0;


        int partyStamina = 0;
        for (Adventurer adventurer : Party) {
            partyStamina += adventurer.stamina;
        }

        currentExpedition.generateNumberOfEvents(partyStamina);


    }

    //Runs the next event in an expedition
    public void nextEvent( int numberofEvents, ArrayList<Adventurer> Party)
    {
        Random r = new Random();

        if (numberofEvents <= 0)
        {
            endExpedition(Party);
            return;
        }


        EventType eventDecider = currentExpedition.getEventType();
        switch (eventDecider)
        {
            case SWAMP:
                currentEvent = swampEvents.get(r.nextInt(swampEvents.size()));
                break;
            case MOUNTAIN:
                currentEvent = mountainEvents.get(r.nextInt(mountainEvents.size()));
                break;
            case FOREST:
                currentEvent = forestEvents.get(r.nextInt(forestEvents.size()));
                break;
        }

        currentEvent.setParty(Party);
        int partyPerception = 0;

        for (Adventurer adventurer : Party)
        {
            partyPerception += adventurer.getPerception();
        }
        currentEvent.setPartyPerception(partyPerception);

        currentEvent.setPerceptionRequired(currentExpedition.getPerceptionRequired());



    }
    //adds the gold to the players earnings
    public void endExpedition(ArrayList<Adventurer> Party)
    {
        gameenvironment.gold = gameenvironment.gold + totalingEarnings;
        gameenvironment.totalGoldGained = gameenvironment.totalGoldGained + totalingEarnings;
        double adventurerPay = 0;
        for(Adventurer adventurer : Party)
        {
            adventurerPay += adventurer.getPay();
            adventurer.completeExpedition();
        }

        gameenvironment.gold = gameenvironment.gold - adventurerPay;


    }



    public ArrayList<Event> getForestEvents() {
        return forestEvents;
    }

    public void setForestEvents(ArrayList<Event> forestEvents) {
        this.forestEvents = forestEvents;
    }

    public ArrayList<Event> getSwampEvents() {
        return swampEvents;
    }

    public void setSwampEvents(ArrayList<Event> swampEvents) {
        this.swampEvents = swampEvents;
    }

    public ArrayList<Event> getMountainEvents() {
        return mountainEvents;
    }

    public void setMountainEvents(ArrayList<Event> mountainEvents) {
        this.mountainEvents = mountainEvents;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public double getTotalingEarnings() {
        return totalingEarnings;
    }

    public void setTotalingEarnings(double totalingEarnings) {
        this.totalingEarnings = totalingEarnings;
    }
}