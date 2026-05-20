package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;



public class ExpeditionSelectionController {


    GameEnvironment gameEnvironment;
    SceneLoader sceneLoader;

    @FXML
    private Label expeditionOneName, expeditionTwoName, expeditionThreeName,
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
    public void initialize() {

        gameEnvironment.expeditionManager.createAllEvents();

        generateNewExpeditions();

        expeditionOneButton.setOnAction(event -> selectExpeditionOne());
        expeditionTwoButton.setOnAction(event -> selectExpeditionTwo());
        expeditionThreeButton.setOnAction(event -> selectExpeditionThree());

        startExpeditionButton.setOnAction(event -> startExpedition());
        guildhallButton.setOnAction(event -> selectGuildHall());
    }

    public void generateNewExpeditions()
    {
        gameEnvironment.expeditionManager.generatePossibleExpeditions();


        expeditionOneName.setText(gameEnvironment.expeditionManager.getExpeditions().get(0).getName());
        expeditionTwoName.setText(gameEnvironment.expeditionManager.getExpeditions().get(1).getName());
        expeditionThreeName.setText(gameEnvironment.expeditionManager.getExpeditions().get(2).getName());

        expeditionOneDescription.setText(gameEnvironment.expeditionManager.getExpeditions().get(0).getDescription());
        expeditionTwoDescription.setText(gameEnvironment.expeditionManager.getExpeditions().get(1).getDescription());
        expeditionThreeDescription.setText(gameEnvironment.expeditionManager.getExpeditions().get(2).getDescription());

        expeditionOnePerception.setText("Perception: " + gameEnvironment.expeditionManager.getExpeditions().get(0).getPerceptionRequired());
        expeditionTwoPerception.setText("Perception: " + gameEnvironment.expeditionManager.getExpeditions().get(1).getPerceptionRequired());
        expeditionThreePerception.setText("Perception: " + gameEnvironment.expeditionManager.getExpeditions().get(2).getPerceptionRequired());

        expeditionOneStamina.setText("Stamina: " + gameEnvironment.expeditionManager.getExpeditions().get(0).getStaminaRequired());
        expeditionTwoStamina.setText("Stamina: " + gameEnvironment.expeditionManager.getExpeditions().get(1).getStaminaRequired());
        expeditionThreeStamina.setText("Stamina: " + gameEnvironment.expeditionManager.getExpeditions().get(2).getStaminaRequired());

    }


    public void selectExpeditionOne() {
        gameEnvironment.expeditionManager.setCurrentExpedition(
                gameEnvironment.expeditionManager.getExpeditions().get(0));
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

