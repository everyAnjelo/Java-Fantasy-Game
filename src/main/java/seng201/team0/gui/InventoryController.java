package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.adventurer.Adventurer;
import seng201.team0.gameEnvironment.GameEnvironment;
import seng201.team0.item.Item;
import seng201.team0.item.ItemType;

public class InventoryController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;
    @FXML
    private Label countSword, countWand, countBow, countDagger, countSmallPotion, countFocusPotion;
    @FXML private Button equipButtonSword, sellButtonSword, equipButtonWand, sellButtonWand, equipButtonBow, sellButtonBow;
    @FXML private Button equipButtonDagger, sellButtonDagger, equipButtonSmallPotion, sellButtonSmallPotion;
    @FXML private Button equipButtonFoucsPotion, sellButtonFoucsPotion;
    @FXML private Button OpenGuildHallButton;
    public InventoryController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
    @FXML
    public void initialize() {
        sellButtonSword.setOnAction(event -> sell(ItemType.SWORD));
        sellButtonWand.setOnAction(event -> sell(ItemType.WAND));
        sellButtonBow.setOnAction(event -> sell(ItemType.BOW));
        sellButtonDagger.setOnAction(event -> sell(ItemType.DAGGER));
        sellButtonSmallPotion.setOnAction(event -> sell(ItemType.POTION, "Small Potion"));
        sellButtonFoucsPotion.setOnAction(event -> sell(ItemType.POTION, "Focus Potion"));

        equipButtonSword.setOnAction(event -> useOrEquip(ItemType.SWORD, null));
        equipButtonWand.setOnAction(event -> useOrEquip(ItemType.WAND, null));
        equipButtonBow.setOnAction(event -> useOrEquip(ItemType.BOW, null));
        equipButtonDagger.setOnAction(event -> useOrEquip(ItemType.DAGGER, null));
        equipButtonSmallPotion.setOnAction(event -> useOrEquip(ItemType.POTION, "Small Potion"));
        equipButtonFoucsPotion.setOnAction(event -> useOrEquip(ItemType.POTION, "Focus Potion"));

        OpenGuildHallButton.setOnAction(event -> sceneLoader.showGuildHall());


        refresh();
    }



    private void refresh() {
        countSword.setText("x" + count(ItemType.SWORD, null));
        countWand.setText("x" + count(ItemType.WAND, null));
        countBow.setText("x" + count(ItemType.BOW, null));
        countDagger.setText("x" + count(ItemType.DAGGER, null));
        countSmallPotion.setText("x" + count(ItemType.POTION, "Small Potion"));
        countFocusPotion.setText("x" + count(ItemType.POTION, "Focus Potion"));
    }

    private int count(ItemType type, String name) {
        int total = 0;
        for (Item item : gameEnvironment.inventory.getItems()) {
            if (item.getType() == type && (name == null || item.getName().equals(name))) {
                total++;
            }
        }
        return total;
    }

    private Item find(ItemType type, String name) {
        for (Item item : gameEnvironment.inventory.getItems()) {
            if (item.getType() == type && (name == null || item.getName().equals(name))) {
                return item;
            }
        }
        return null;
    }

    private void sell(ItemType type) {
        sell(type, null);
    }

    private void sell(ItemType type, String name) {
        Item item = find(type, name);
        if (item == null) {
            GuiHelper.showError("You do not own this item.");
            return;
        }
        gameEnvironment.shop.sellItem(item, gameEnvironment);
        refresh();
    }

    private void useOrEquip(ItemType type, String name) {
        Item item = find(type, name);
        if (item == null) {
            GuiHelper.showError("You do not own this item.");
            return;
        }

        Adventurer target = findTarget(item);
        if (target == null) {
            GuiHelper.showError("No adventurer can use this item right now.");
            return;
        }

        if (item.getType() == ItemType.POTION) {
            target.usePotion(item);
        } else {
            target.equipWeapon(item);
        }
        gameEnvironment.inventory.removeItem(item);
        GuiHelper.showInfo("Item Used", item.getName() + " was used on " + target.getName() + ".");
        refresh();
    }

    private Adventurer findTarget(Item item) {
        for (Adventurer adventurer : gameEnvironment.adventurerManager.getMainTeam()) {
            if (item.canBeUsedBy(adventurer)) {
                return adventurer;
            }
        }
        for (Adventurer adventurer : gameEnvironment.adventurerManager.getReserveTeam()) {
            if (item.canBeUsedBy(adventurer)) {
                return adventurer;
            }
        }
        return null;
    }
}
