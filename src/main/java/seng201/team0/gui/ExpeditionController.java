package seng201.team0.gui;

import seng201.team0.GameEnvironment;

public class ExepeditonController {

    @FXML
    private Label  exepeditonName, eventDescription, optionOne, optionTwo;


    @FXML private Button buttonOptionOne, buttonOptionTwo;

    GameEnvironment gameEnvironment;
    SceneLoader sceneLoader;

    public ExepeditonController(SceneLoader sceneLoader)
    {
        this.sceneLoader = sceneLoader;
        gameEnvironment = this.sceneLoader.getGameEnvironment();

    }

    public void initialize()
    {
        exepeditonName.setText(gameEnvironment.expeditionManager.getCurrentExpedition().getName());
        eventDescription.setText(gameEnvironment.expeditionManager.getCurrentEvent().getDescription());
        optionOne.setText(gameEnvironment.expeditionManager.getCurrentEvent().getOptionOne());
        optionTwo.setText(gameEnvironment.expeditionManager.getCurrentEvent().getOptionTwo());
    }

    public void checkIfEnding()
    {

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

