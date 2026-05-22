package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.adventurer.Adventurer;
import seng201.team0.gameEnvironment.GameEnvironment;

import java.util.ArrayList;
/**
 * Controller for the guild overview scene.
 *
 * This shows the main team and reserve team.
 */
public class GuildOverviewController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;

    @FXML private Label goldLabel;
    @FXML private Button OpenGuildHallButton;

    @FXML private Label mainRole1, mainName1, mainStamina1, mainPerception1, mainPay1;
    @FXML private Label mainRole2, mainName2, mainStamina2, mainPerception2, mainPay2;
    @FXML private Label mainRole3, mainName3, mainStamina3, mainPerception3, mainPay3;
    @FXML private Label mainRole4, mainName4, mainStamina4, mainPerception4, mainPay4;
    @FXML private Label mainRole5, mainName5, mainStamina5, mainPerception5, mainPay5;
    @FXML private Button mainRetireButton1, mainRetireButton2, mainRetireButton3, mainRetireButton4, mainRetireButton5;
    @FXML private Button mainMoveButton1, mainMoveButton2, mainMoveButton3, mainMoveButton4, mainMoveButton5;

    @FXML private Label reserveRole1, reserveName1, reserveStamina1, reservePerception1, reservePay1;
    @FXML private Label reserveRole2, reserveName2, reserveStamina2, reservePerception2, reservePay2;
    @FXML private Label reserveRole3, reserveName3, reserveStamina3, reservePerception3, reservePay3;
    @FXML private Label reserveRole4, reserveName4, reserveStamina4, reservePerception4, reservePay4;
    @FXML private Label reserveRole5, reserveName5, reserveStamina5, reservePerception5, reservePay5;
    @FXML private Button reserveRetireButton1, reserveRetireButton2, reserveRetireButton3, reserveRetireButton4, reserveRetireButton5;
    @FXML private Button reserveUseButton1, reserveUseButton2, reserveUseButton3, reserveUseButton4, reserveUseButton5;

    /**
     * Creates the controller and gets the game data.
     *
     * @param sceneLoader used to change scenes
     */
    public GuildOverviewController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
    /**
     * Sets up the buttons when the scene opens.
     */
    public void initialize() {
        OpenGuildHallButton.setOnAction(event -> sceneLoader.showGuildHall());

        mainMoveButton1.setOnAction(event -> moveMainToReserve(0));
        mainMoveButton2.setOnAction(event -> moveMainToReserve(1));
        mainMoveButton3.setOnAction(event -> moveMainToReserve(2));
        mainMoveButton4.setOnAction(event -> moveMainToReserve(3));
        mainMoveButton5.setOnAction(event -> moveMainToReserve(4));

        mainRetireButton1.setOnAction(event -> retireMain(0));
        mainRetireButton2.setOnAction(event -> retireMain(1));
        mainRetireButton3.setOnAction(event -> retireMain(2));
        mainRetireButton4.setOnAction(event -> retireMain(3));
        mainRetireButton5.setOnAction(event -> retireMain(4));

        reserveUseButton1.setOnAction(event -> moveReserveToMain(0));
        reserveUseButton2.setOnAction(event -> moveReserveToMain(1));
        reserveUseButton3.setOnAction(event -> moveReserveToMain(2));
        reserveUseButton4.setOnAction(event -> moveReserveToMain(3));
        reserveUseButton5.setOnAction(event -> moveReserveToMain(4));

        reserveRetireButton1.setOnAction(event -> retireReserve(0));
        reserveRetireButton2.setOnAction(event -> retireReserve(1));
        reserveRetireButton3.setOnAction(event -> retireReserve(2));
        reserveRetireButton4.setOnAction(event -> retireReserve(3));
        reserveRetireButton5.setOnAction(event -> retireReserve(4));

        refresh();
    }
    /**
     * Updates the gold label and all adventurer slots.
     */
    private void refresh() {
        goldLabel.setText("Gold: " + GuiHelper.money(gameEnvironment.gold));
        refreshSlot(gameEnvironment.adventurerManager.getMainTeam(), 0, mainRole1, mainName1, mainStamina1, mainPerception1, mainPay1, mainMoveButton1, mainRetireButton1);
        refreshSlot(gameEnvironment.adventurerManager.getMainTeam(), 1, mainRole2, mainName2, mainStamina2, mainPerception2, mainPay2, mainMoveButton2, mainRetireButton2);
        refreshSlot(gameEnvironment.adventurerManager.getMainTeam(), 2, mainRole3, mainName3, mainStamina3, mainPerception3, mainPay3, mainMoveButton3, mainRetireButton3);
        refreshSlot(gameEnvironment.adventurerManager.getMainTeam(), 3, mainRole4, mainName4, mainStamina4, mainPerception4, mainPay4, mainMoveButton4, mainRetireButton4);
        refreshSlot(gameEnvironment.adventurerManager.getMainTeam(), 4, mainRole5, mainName5, mainStamina5, mainPerception5, mainPay5, mainMoveButton5, mainRetireButton5);

        refreshSlot(gameEnvironment.adventurerManager.getReserveTeam(), 0, reserveRole1, reserveName1, reserveStamina1, reservePerception1, reservePay1, reserveUseButton1, reserveRetireButton1);
        refreshSlot(gameEnvironment.adventurerManager.getReserveTeam(), 1, reserveRole2, reserveName2, reserveStamina2, reservePerception2, reservePay2, reserveUseButton2, reserveRetireButton2);
        refreshSlot(gameEnvironment.adventurerManager.getReserveTeam(), 2, reserveRole3, reserveName3, reserveStamina3, reservePerception3, reservePay3, reserveUseButton3, reserveRetireButton3);
        refreshSlot(gameEnvironment.adventurerManager.getReserveTeam(), 3, reserveRole4, reserveName4, reserveStamina4, reservePerception4, reservePay4, reserveUseButton4, reserveRetireButton4);
        refreshSlot(gameEnvironment.adventurerManager.getReserveTeam(), 4, reserveRole5, reserveName5, reserveStamina5, reservePerception5, reservePay5, reserveUseButton5, reserveRetireButton5);
    }

    /**
     * Shows one adventurer slot.
     *
     * If the slot is empty, it shows blank values.
     *
     * @param list the team list
     * @param index the slot number
     * @param role role label
     * @param name name label
     * @param stamina stamina label
     * @param perception perception label
     * @param pay pay label
     * @param actionButton move/use button
     * @param retireButton retire button
     */
    private void refreshSlot(ArrayList<Adventurer> list, int index, Label role, Label name, Label stamina, Label perception, Label pay, Button actionButton, Button retireButton) {
        if (index >= list.size()) {
            role.setText("Role: -");
            name.setText("Name: -");
            stamina.setText("Stamina: -");
            perception.setText("Perception: -");
            pay.setText("Pay: -");
            actionButton.setDisable(true);
            retireButton.setDisable(true);
            return;
        }
        Adventurer adventurer = list.get(index);
        role.setText("Role: " + adventurer.getRole());
        name.setText("Name: " + adventurer.getName());
        stamina.setText("Stamina: " + adventurer.getStamina());
        perception.setText("Perception: " + adventurer.getPerception());
        pay.setText("Pay: " + GuiHelper.money(adventurer.getPay()));
        actionButton.setDisable(false);
        retireButton.setDisable(false);
    }

    /**
     * Moves an adventurer from main team to reserve team.
     *
     * @param index the adventurer position
     */
    private void moveMainToReserve(int index) {
        gameEnvironment.adventurerManager.addToReserve(gameEnvironment.adventurerManager.getMainTeam().get(index));
        refresh();
    }
    /**
     * Moves an adventurer from reserve team to main team.
     *
     * @param index the adventurer position
     */
    private void moveReserveToMain(int index) {
        gameEnvironment.adventurerManager.addToMain(gameEnvironment.adventurerManager.getReserveTeam().get(index));
        refresh();
    }

    /**
     * Retires an adventurer from the main team.
     *
     * @param index the adventurer position
     */

    private void retireMain(int index) {
        gameEnvironment.adventurerManager.retireAdventurer(gameEnvironment.adventurerManager.getMainTeam().get(index));
        refresh();
    }

    /**
     * Retires an adventurer from the reserve team.
     *
     * @param index the adventurer position
     */
    private void retireReserve(int index) {
        gameEnvironment.adventurerManager.retireAdventurer(gameEnvironment.adventurerManager.getReserveTeam().get(index));
        refresh();
    }
}
