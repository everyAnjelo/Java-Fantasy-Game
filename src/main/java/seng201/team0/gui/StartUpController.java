package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
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
    @FXML private Label numberOfExpeditionDisplay,errorLabel;
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
        String guildName = GuildNameInput.getText();

        if (guildName == null || !guildName.matches("[a-zA-Z0-9]{3,15}")) {
            errorLabel.setTextFill(Paint.valueOf("red"));
            GuiHelper.showError("Guild name must be 3 to 15 letters/numbers only.");
            return;
        }

        Adventurer starterOne = createStarter(starterOneChoice.getValue(), starterOneName.getText(), 1);
        Adventurer starterTwo = createStarter(starterTwoChoice.getValue(), starterTwoName.getText(), 2);
        Adventurer starterThree = createStarter(starterThreeChoice.getValue(), starterThreeName.getText(), 3);



        gameEnvironment.guildName = guildName;
        gameEnvironment.totalExpeditions = (int) expeditionInput.getValue();

        if (easyRadioButton.isSelected()) {
            gameEnvironment.difficulty = Difficulty.EASY;
            gameEnvironment.gold = 200;
        } else {
            gameEnvironment.difficulty = Difficulty.HARD;
            gameEnvironment.gold = 350;
        }

        gameEnvironment.adventurerManager.addToMain(starterOne);
        gameEnvironment.adventurerManager.addToMain(starterTwo);
        gameEnvironment.adventurerManager.addToMain(starterThree);

        sceneLoader.showGuildHall();
    }

    private Adventurer createStarter(String type, String name, int slotNumber) {


        String finalName = name;
        if (finalName == null || finalName.isBlank()) {
            finalName = "Starter " + slotNumber;
        }

        if (!finalName.matches("[a-zA-Z0-9 ]{1,15}")) {
            GuiHelper.showError("Starter " + slotNumber + " name must be 1 to 15 letters/numbers only.");
            return null;
        }

        if (type.equals("Warrior")) {
            return new Warrior(finalName);
        } else if (type.equals("Mage")) {
            return new Mage(finalName);
        } else if (type.equals("Marksman")) {
            return new Marksman(finalName);
        } else if (type.equals("Rogue")) {
            return new Rogue(finalName);
        }

        GuiHelper.showError("Invalid starter type for starter " + slotNumber + ".");
        return null;
    }

}
