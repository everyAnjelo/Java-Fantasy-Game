package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import seng201.team0.adventurer.Adventurer;
import seng201.team0.gameEnvironment.Difficulty;
import seng201.team0.gameEnvironment.GameEnvironment;
import seng201.team0.adventurer.Mage;
import seng201.team0.adventurer.Marksman;
import seng201.team0.adventurer.Rogue;
import seng201.team0.adventurer.Warrior;

/**
 * Controller for the startup scene.
 *
 * This class handles the first screen of the game where the player
 * enters the guild name, chooses the number of expeditions, difficulty,
 * and starter adventurers.
 */
public class StartUpController {

    private final SceneLoader sceneLoader;
    private final GameEnvironment gameEnvironment;

    @FXML private TextField GuildNameInput;
    @FXML private Label numberOfExpeditionDisplay, errorLabel;
    @FXML private Slider expeditionInput;
    @FXML private RadioButton easyRadioButton, hardRadioButton;
    @FXML private ComboBox<String> starterOneChoice, starterTwoChoice, starterThreeChoice;
    @FXML private TextField starterOneName, starterTwoName, starterThreeName;

    @FXML private Button StartButton;

    /**
     * Creates the startup controller.
     * This also gets the game environment from the scene loader so the
     * game data can be saved in one place.
     * @param sceneLoader the scene loader used to change scenes
     */
    public StartUpController(SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
        gameEnvironment = this.sceneLoader.getGameEnvironment();
    }

    /**
     * Sets up the startup scene when it first loads.
     * This puts values into the combo boxes, updates the expedition number,
     * and makes the start button run the start game method.
     */
    @FXML
    public void initialize() {
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

    /**
     * Starts the game after the player clicks the start button.
     * This checks the guild name, creates the starter adventurers,
     * sets the difficulty, gives the starting gold, and then moves
     * to the guild hall scene.
     */
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
            gameEnvironment.gold = 400;
        } else {
            gameEnvironment.difficulty = Difficulty.HARD;
            gameEnvironment.gold = 250;
        }

        gameEnvironment.adventurerManager.addToMain(starterOne);
        gameEnvironment.adventurerManager.addToMain(starterTwo);
        gameEnvironment.adventurerManager.addToMain(starterThree);

        sceneLoader.showGuildHall();
    }

    /**
     * Makes a starter adventurer from the type chosen by the player.
     * If the player does not type a name, a default starter name is used.
     * @param type the type of adventurer chosen
     * @param name the name typed by the player
     * @param slotNumber the starter number, used for the default name
     * @return the new adventurer that was made
     */
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