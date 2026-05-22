package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.gameEnvironment.GameEnvironment;


/**
 * ExpeditionSelection Controller
 * A class for intefacing between the GUI and the Expedition selection
 */
public class ExpeditionSelectionController {


    GameEnvironment gameEnvironment;
    SceneLoader sceneLoader;

    @FXML
    private Label expeditionOneName, expeditionTwoName, expeditionThreeName,
            expeditionOneDescription, expeditionTwoDescription, expeditionThreeDescription,
            expeditionOneStamina, expeditionTwoStamina, expeditionThreeStamina,
            expeditionOnePerception, expeditionTwoPerception, expeditionThreePerception;

    @FXML private Button expeditionOneButton, expeditionTwoButton, expeditionThreeButton, startExpeditionButton, guildhallButton;

    /**
     * Constructor for the ExpeditionSelectionController
     * @param sceneLoader for loading the new scenes
     */

    public ExpeditionSelectionController(SceneLoader sceneLoader)
    {
        this.sceneLoader = sceneLoader;
        gameEnvironment = this.sceneLoader.getGameEnvironment();

    }
    /**
     * initializing the Expedition Selection page
     */
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

    /**
     * Method for generating new expeditions using the expedition manager
     */
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


    /**
     * Method for interfacing to select expedition One
     */
    public void selectExpeditionOne() {
        gameEnvironment.expeditionManager.setCurrentExpedition(
                gameEnvironment.expeditionManager.getExpeditions().get(0));
    }

    /**
     * Method for interfacing to select expedition Two
     */
    public void selectExpeditionTwo()
    {
        gameEnvironment.expeditionManager.setCurrentExpedition(gameEnvironment.expeditionManager.getExpeditions().get(1));
    }

    /**
     * Method for interfacing to select expedition Three
     */
    public void selectExpeditionThree()
    {
        gameEnvironment.expeditionManager.setCurrentExpedition(gameEnvironment.expeditionManager.getExpeditions().get(2));
    }

    /**
     * Method for interfacing to Change to the Guild hall
     */
    public void selectGuildHall()
    {
        sceneLoader.showGuildHall();
    }

    /**
     * Method for starting the Expedition and changing the scene to the event scene
     */
    public void startExpedition() {
        if (gameEnvironment.expeditionManager.getCurrentExpedition() == null)
        {
            System.out.println("Choose an Expedition");

        }

        else
        {
            gameEnvironment.expeditionManager.startExpedition(gameEnvironment.adventurerManager.getMainTeam());

            gameEnvironment.expeditionManager.nextEvent(gameEnvironment.adventurerManager.getMainTeam());
            sceneLoader.showExpedition();

        }
    }

}

