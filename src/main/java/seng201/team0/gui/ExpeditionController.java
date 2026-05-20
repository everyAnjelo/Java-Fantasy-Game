package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;

public class ExpeditionController {

    @FXML
    private Label expeditionName, eventDescription, optionOne, optionTwo;


    @FXML private Button buttonOptionOne, buttonOptionTwo;

    GameEnvironment gameEnvironment;
    SceneLoader sceneLoader;

    public ExpeditionController(SceneLoader sceneLoader)
    {
        this.sceneLoader = sceneLoader;
        gameEnvironment = this.sceneLoader.getGameEnvironment();

    }

    public void initialize()
    {
        expeditionName.setText(gameEnvironment.expeditionManager.getCurrentExpedition().getName());
        eventDescription.setText(gameEnvironment.expeditionManager.getCurrentEvent().getDescription());
        buttonOptionOne.setText(gameEnvironment.expeditionManager.getCurrentEvent().getOptionOne());
        buttonOptionTwo.setText(gameEnvironment.expeditionManager.getCurrentEvent().getOptionTwo());

        buttonOptionOne.setOnAction(event -> optionOne());
        buttonOptionTwo.setOnAction(event -> optionTwo());
    }

    public void checkIfEnding()
    {
        System.out.println(gameEnvironment.expeditionManager.getNumberOfEvents());
        //go to results screen
        if (gameEnvironment.gold <= 0 || gameEnvironment.expeditionsCompleted == gameEnvironment.totalExpeditions)
        {
            sceneLoader.showResult();
        }
        else if (gameEnvironment.expeditionManager.getNumberOfEvents() <= 0)
        {
            sceneLoader.showGuildHall();
        }
        else
        {
            initialize();
        }
    }

    public void optionOne()
    {
        gameEnvironment.expeditionManager.getCurrentEvent().chooseOptionOne();

        gameEnvironment.expeditionManager.nextEvent(gameEnvironment.adventurerManager.getMainTeam());
        checkIfEnding();

    }
    public void optionTwo()
    {
        gameEnvironment.expeditionManager.getCurrentEvent().chooseOptionTwo();
        gameEnvironment.expeditionManager.nextEvent(gameEnvironment.adventurerManager.getMainTeam());
        checkIfEnding();
    }


}

