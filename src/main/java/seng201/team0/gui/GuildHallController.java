package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import seng201.team0.Adventurer;
import seng201.team0.GameEnvironment;

import java.lang.classfile.attribute.LineNumberInfo;

public class GuildHallController {

    GameEnvironment gameEnvironment;
    SceneLoader sceneLoader;
    @FXML
    private Label goldLabel;
    @FXML
    private Label expeditionCountLabel;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem inventoryMenuItem;
    @FXML
    private MenuItem guildOverviewMenuItem;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private MenuItem shopMenuItem;


    @FXML
    private Label recruitRoleLabel1, recruitNameLabel1, recruitStaminaLabel1, recruitPerceptionLabel1, recruitPayLabel1;
    @FXML
    private Label recruitRoleLabel2, recruitNameLabel2, recruitStaminaLabel2, recruitPerceptionLabel2, recruitPayLabel2;
    @FXML
    private Label recruitRoleLabel3, recruitNameLabel3, recruitStaminaLabel3, recruitPerceptionLabel3, recruitPayLabel3;
    @FXML
    private Label recruitRoleLabel4, recruitNameLabel4, recruitStaminaLabel4, recruitPerceptionLabel4, recruitPayLabel4;
    @FXML
    private Button hireButton1, hireButton2, hireButton3, hireButton4;

    @FXML
    private Label mainRoleLabel1, mainNameLabel1, mainStaminaLabel1, mainPerceptionLabel1, mainPayLabel1;
    @FXML
    private Label mainRoleLabel2, mainNameLabel2, mainStaminaLabel2, mainPerceptionLabel2, mainPayLabel2;
    @FXML
    private Label mainRoleLabel3, mainNameLabel3, mainStaminaLabel3, mainPerceptionLabel3, mainPayLabel3;
    @FXML
    private Label mainRoleLabel4, mainNameLabel4, mainStaminaLabel4, mainPerceptionLabel4, mainPayLabel4;
    @FXML
    private Label mainRoleLabel5, mainNameLabel5, mainStaminaLabel5, mainPerceptionLabel5, mainPayLabel5;
    @FXML
    private Button startExpeditionButton;

    public GuildHallController(SceneLoader sceneLoader){

        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();


    }

    public void initialize(){
        inventoryMenuItem.setOnAction(event -> sceneLoader.showInventory());
        guildOverviewMenuItem.setOnAction((event -> sceneLoader.showGuildOverview()));
        shopMenuItem.setOnAction(event -> sceneLoader.showShop());
        exitMenuItem.setOnAction(event -> {gameEnvironment.finishGame(false);sceneLoader.showResult();});


        hireButton1.setOnAction(event -> hire(0));
        hireButton2.setOnAction(event -> hire(1));
        hireButton3.setOnAction(event -> hire(2));
        hireButton4.setOnAction(event -> hire(3));
        startExpeditionButton.setOnAction(event -> sceneLoader.showExpeditionSelection());

        refresh();

    }
    public void hire(int index){
        Adventurer tempplate =gameEnvironment.guildHall.getAvailableAdventurers().get(index);

        Adventurer adventurer = gameEnvironment.guildHall.createAdventurer(index + 1 , tempplate.getName() + gameEnvironment.expeditionsCompleted);

        if(gameEnvironment.adventurerManager.getMainTeam().size() + gameEnvironment.adventurerManager.getReserveTeam().size() == 10 ){
            GuiHelper.showError("Cant hire more adventuter");
            return;
        }
        if (gameEnvironment.gold < adventurer.getHiringCost()) {
            GuiHelper.showError("Not enough gold to hire this adventurer.");
            return;
        }

        gameEnvironment.gold -= adventurer.getHiringCost();
        if (gameEnvironment.adventurerManager.getMainTeam().size() < gameEnvironment.adventurerManager.getMaxMainTeam()) {
            gameEnvironment.adventurerManager.addToMain(adventurer);
        } else {
            gameEnvironment.adventurerManager.addToReserve(adventurer);
        }
        refresh();
    }

    private void refresh() {
        goldLabel.setText("Gold: " + GuiHelper.money(gameEnvironment.gold));
        expeditionCountLabel.setText("Expeditions: " + gameEnvironment.expeditionsCompleted + "/" + gameEnvironment.totalExpeditions);
        refreshRecruit(0, recruitRoleLabel1, recruitNameLabel1, recruitStaminaLabel1, recruitPerceptionLabel1, recruitPayLabel1, hireButton1);
        refreshRecruit(1, recruitRoleLabel2, recruitNameLabel2, recruitStaminaLabel2, recruitPerceptionLabel2, recruitPayLabel2, hireButton2);
        refreshRecruit(2, recruitRoleLabel3, recruitNameLabel3, recruitStaminaLabel3, recruitPerceptionLabel3, recruitPayLabel3, hireButton3);
        refreshRecruit(3, recruitRoleLabel4, recruitNameLabel4, recruitStaminaLabel4, recruitPerceptionLabel4, recruitPayLabel4, hireButton4);

        refreshMain(0, mainRoleLabel1, mainNameLabel1, mainStaminaLabel1, mainPerceptionLabel1, mainPayLabel1);
        refreshMain(1, mainRoleLabel2, mainNameLabel2, mainStaminaLabel2, mainPerceptionLabel2, mainPayLabel2);
        refreshMain(2, mainRoleLabel3, mainNameLabel3, mainStaminaLabel3, mainPerceptionLabel3, mainPayLabel3);
        refreshMain(3, mainRoleLabel4, mainNameLabel4, mainStaminaLabel4, mainPerceptionLabel4, mainPayLabel4);
        refreshMain(4, mainRoleLabel5, mainNameLabel5, mainStaminaLabel5, mainPerceptionLabel5, mainPayLabel5);
    }

    private void refreshRecruit(int index, Label role, Label name, Label stamina, Label perception, Label pay, Button hireButton) {
        Adventurer adventurer = gameEnvironment.guildHall.getAvailableAdventurers().get(index);
        role.setText("Role: " + adventurer.getRole());
        name.setText("Name: " + adventurer.getName());
        stamina.setText("Stamina: " + adventurer.getStamina());
        perception.setText("Perception: " + adventurer.getPerception());
        pay.setText("Pay: " + GuiHelper.money(adventurer.getPay()) + " / expedition");
        hireButton.setText(GuiHelper.money(adventurer.getHiringCost()) + "g");
    }

    private void refreshMain(int index, Label role, Label name, Label stamina, Label perception, Label pay) {
        if (index >= gameEnvironment.adventurerManager.getMainTeam().size()) {
            role.setText("Role: Empty");
            name.setText("Name: -");
            stamina.setText("Stamina: -");
            perception.setText("Perception: -");
            pay.setText("Pay: -");
            return;
        }
        Adventurer adventurer = gameEnvironment.adventurerManager.getMainTeam().get(index);
        role.setText("Role: " + adventurer.getRole());
        name.setText("Name: " + adventurer.getName());
        stamina.setText("Stamina: " + adventurer.getStamina());
        perception.setText("Perception: " + adventurer.getPerception());
        pay.setText("Pay: " + GuiHelper.money(adventurer.getPay()));
    }
}
