package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.gameEnvironment.GameEnvironment;

/**
 * ExpeditionController used for interfacing with the GUI and running the expeditions, choosing the event options to go on
 * and displaying the stats of the expeditions
 */
public class ExpeditionController {

    @FXML
    private Label expeditionName, eventDescription, optionOne, optionTwo;


    @FXML private Button buttonOptionOne, buttonOptionTwo;

    GameEnvironment gameEnvironment;
    SceneLoader sceneLoader;

    /**Constructor for ExpeditionController
     * @param sceneLoader for loading the new scenes
     */

    public ExpeditionController(SceneLoader sceneLoader)
    {
        this.sceneLoader = sceneLoader;
        gameEnvironment = this.sceneLoader.getGameEnvironment();

    }
    /**
     * initializing the Event page
     */
    public void initialize()
    {
        expeditionName.setText(gameEnvironment.expeditionManager.getCurrentExpedition().getName());
        eventDescription.setText(gameEnvironment.expeditionManager.getCurrentEvent().getDescription());
        buttonOptionOne.setText(gameEnvironment.expeditionManager.getCurrentEvent().getOptionOne());
        buttonOptionTwo.setText(gameEnvironment.expeditionManager.getCurrentEvent().getOptionTwo());

        buttonOptionOne.setOnAction(event -> optionOne());
        buttonOptionTwo.setOnAction(event -> optionTwo());
    }
    /**
     * a Method for checking if the result screen needs to show and the game needs to end
     */
    public void checkIfEnding()
    {
        System.out.println(gameEnvironment.expeditionManager.getNumberOfEvents());
        //go to results screen
        if (gameEnvironment.gold < 0)
        {
            sceneLoader.won = false;
            sceneLoader.showResult();
        } else if (gameEnvironment.expeditionsCompleted == gameEnvironment.totalExpeditions) {
            sceneLoader.won = true;
            sceneLoader.showResult();
        } else if (gameEnvironment.expeditionManager.getNumberOfEvents() <= 0)
        {
            //gameEnvironment.expeditionsCompleted++;
            sceneLoader.showGuildHall();
        }
        else
        {
            initialize();
        }
    }

    /**
     * The interface for selecting option one of the event
     */
    public void optionOne()
    {
        gameEnvironment.expeditionManager.getCurrentEvent().chooseOptionOne();

        gameEnvironment.expeditionManager.nextEvent(gameEnvironment.adventurerManager.getMainTeam());
        checkIfEnding();

    }

    /**
     * The interface for selectiong option 2 of the event
     */
    public void optionTwo()
    {
        gameEnvironment.expeditionManager.getCurrentEvent().chooseOptionTwo();
        gameEnvironment.expeditionManager.nextEvent(gameEnvironment.adventurerManager.getMainTeam());
        checkIfEnding();
    }


}

