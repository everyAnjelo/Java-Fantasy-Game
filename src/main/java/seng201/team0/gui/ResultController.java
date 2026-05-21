package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.gameEnvironment.GameEnvironment;

/**
 * Controller for the result scene.
 * This class shows the final result of the game.
 * It displays the guild name, if the player won or lost,
 * the number of expeditions, and the total gold earned.
 */
public class ResultController {

    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;

    @FXML private Label guildNameLabel;
    @FXML private Label winLoseLabel;
    @FXML private Label chosenExpeditionLabel;
    @FXML private Label completedExpeditionLabel;
    @FXML private Label totalGoldLabel;
    @FXML private Button exitButton;

    /**
     * Creates the result controller.
     * This gets the scene loader and game environment so the result
     * scene can show the final game information.
     * @param sceneLoader the scene loader used in the game
     */
    public ResultController(SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();
    }

    /**
     * Sets up the result scene when it opens.
     * This puts the game information into the labels and also makes
     * the exit button close the game.
     */
    @FXML
    public void initialize() {
        guildNameLabel.setText(gameEnvironment.guildName);
        winLoseLabel.setText(sceneLoader.won ? "Won" : "Lost / Finished");
        chosenExpeditionLabel.setText(String.valueOf(gameEnvironment.totalExpeditions));
        completedExpeditionLabel.setText(String.valueOf(gameEnvironment.expeditionsCompleted));
        totalGoldLabel.setText(GuiHelper.money(gameEnvironment.totalGoldGained) + " Gold ");

        exitButton.setOnAction(event -> Platform.exit());
    }
}