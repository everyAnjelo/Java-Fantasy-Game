package seng201.team0.gui;

import seng201.team0.GameEnvironment;

import java.awt.*;

public class ExpeditionSelectionController {


    GameEnvironment gameEnvironment;
    SceneLoader sceneLoader;

    @FXML private Label expeditionOneName, expeditionTwoName, expeditionThreeName,
            expeditionOneDescription, expeditionTwoDescription, expeditionThreeDescription,
            expeditionOneStamina, expeditionTwoStamina, expeditionThreeStamina,
            expeditionOnePerception, expeditionTwoPerception, expeditionThreePerception;

    @FXML private Button expeditionOneButton, expeditionTwoButton, expeditionThreeButton, startExpeditionButton, guildhallButton;



    public ExpeditionSelectionController(SceneLoader sceneLoader)
    {
        this.sceneLoader = sceneLoader;
        gameEnvironment = this.sceneLoader.getGameEnvironment();

    }

    @FXML
    public void initialize()
    {

        expeditionOneName.setText( gameEnvironment.expeditionManager.getExpeditions().getFirst().getName());
        expeditionTwoName.setText( gameEnvironment.expeditionManager.getExpeditions().get(1).getName());
        expeditionThreeName.setText( gameEnvironment.expeditionManager.getExpeditions().get(2).getName());

        expeditionOneDescription.setText( gameEnvironment.expeditionManager.getExpeditions().getFirst().getDescription());
        expeditionTwoDescription.setText( gameEnvironment.expeditionManager.getExpeditions().get(1).getDescription());
        expeditionThreeDescription.setText( gameEnvironment.expeditionManager.getExpeditions().get(2).getDescription());

        expeditionOnePerception.setText(String.valueOf(gameEnvironment.expeditionManager.getExpeditions().get(0).getPerceptionRequired()));
        expeditionTwoPerception.setText(String.valueOf(gameEnvironment.expeditionManager.getExpeditions().get(1).getPerceptionRequired()));
        expeditionThreePerception.setText(String.valueOf(gameEnvironment.expeditionManager.getExpeditions().get(2).getPerceptionRequired()));

        expeditionOneStamina.setText(String.valueOf(gameEnvironment.expeditionManager.getExpeditions().get(0).getStaminaRequired()));
        expeditionTwoStamina.setText(String.valueOf(gameEnvironment.expeditionManager.getExpeditions().get(1).getStaminaRequired()));
        expeditionThreeStamina.setText(String.valueOf(gameEnvironment.expeditionManager.getExpeditions().get(2).getStaminaRequired()));

        expeditionOneButton.setOnAction(event -> selectExpeditionOne());
        expeditionTwoButton.setOnAction(event -> selectExpeditionTwo());
        expeditionThreeButton.setOnAction(event -> selectExpeditionThree());

    }


    public void selectExpeditionOne()
    {
        gameEnvironment.expeditionManager.setCurrentExpedition(gameEnvironment.expeditionManager.getExpeditions().getFirst());
    }

    public void selectExpeditionTwo()
    {
        gameEnvironment.expeditionManager.setCurrentExpedition(gameEnvironment.expeditionManager.getExpeditions().get(1));
    }

    public void selectExpeditionThree()
    {
        gameEnvironment.expeditionManager.setCurrentExpedition(gameEnvironment.expeditionManager.getExpeditions().get(2));
    }

    public void selectGuildHall()
    {
        sceneLoader.showGuildHall();
    }

    public void startExpedition() {
        if (gameEnvironment.expeditionManager.getCurrentExpedition() == null)
        {
            System.out.println("Choose an Expedition");

        }

        else
        {
            gameEnvironment.expeditionManager.startExpedition(gameEnvironment.adventurerManager.getMainTeam());

            gameEnvironment.expeditionManager.nextEvent(gameEnvironment.adventurerManager.getMainTeam());
            sceneLoader.showExpeditionSelection();

        }
    }


}

