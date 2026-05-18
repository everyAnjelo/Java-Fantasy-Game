package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import seng201.team0.Adventurer;
import seng201.team0.Difficulty;
import seng201.team0.ExpeditionManager;
import seng201.team0.GameEnvironment;
import seng201.team0.Mage;
import seng201.team0.Marksman;
import seng201.team0.Rogue;
import seng201.team0.Warrior;

public class StartUpController {
    private final SceneLoader sceneLoader;
    private final GameEnvironment gameEnvironment;

    @FXML private TextField GuildNameInput;
    @FXML private Label numberOfExpeditionDisplay;
    @FXML private Slider expeditionInput;
    @FXML private RadioButton easyRadioButton, hardRadioButton;
    @FXML private ComboBox<String> starterOneChoice, starterTwoChoice, starterThreeChoice;
    @FXML private TextField starterOneName, starterTwoName, starterThreeName;

    @FXML private Button StartButton;

    public StartUpController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        gameEnvironment = this.sceneLoader.getGameEnvironment();

    }

    @FXML
    public void initialize(){
        numberOfExpeditionDisplay.setText(String.valueOf((int) expeditionInput.getValue()));
        expeditionInput.valueProperty().addListener((obs, oldValue, newValue) ->
                numberOfExpeditionDisplay.setText(String.valueOf(newValue.intValue())));

        starterOneChoice.getItems().addAll("Warrior", "Mage", "Marksman", "Rogue");
        starterTwoChoice.getItems().addAll("Warrior", "Mage", "Marksman", "Rogue");
        starterThreeChoice.getItems().addAll("Warrior", "Mage", "Marksman", "Rogue");

        starterOneChoice.setValue("Warrior");
        starterTwoChoice.setValue("Mage");
        starterThreeChoice.setValue("Marksman");

        StartButton.setOnAction(event -> startGame());

    }

    private void startGame() {
    }


}
