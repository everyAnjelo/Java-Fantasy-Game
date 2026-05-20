package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;

public class ResultController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;

    @FXML private Label guildNameLabel;
    @FXML private Label winLoseLabel;
    @FXML private Label chosenExpeditionLabel;
    @FXML private Label completedExpeditionLabel;
    @FXML private Label totalGoldLabel;
    @FXML private Button exitButton;
    public ResultController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
    @FXML
    public void initialize() {

        guildNameLabel.setText(gameEnvironment.guildName);
        winLoseLabel.setText(gameEnvironment.isRunning ? "Won" : "Lost / Finished");
        chosenExpeditionLabel.setText(String.valueOf(gameEnvironment.totalExpeditions));
        completedExpeditionLabel.setText(String.valueOf(gameEnvironment.expeditionsCompleted));
        totalGoldLabel.setText(GuiHelper.money(gameEnvironment.totalGoldGained) + " Gold: ");

        exitButton.setOnAction(event -> Platform.exit());
    }
}
