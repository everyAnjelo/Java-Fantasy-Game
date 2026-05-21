package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;
import seng201.team0.Item;

/**
 * Controller for the shop scene.
 *
 * This class lets the player buy items from the shop.
 * It also uses the same game environment so the gold and inventory
 * are shared with the rest of the game.
 */
public class ShopController {

    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;

    @FXML
    private Label priceSword, priceWand, priceBow, priceDagger, priceSmallPotion, priceFocusPotion, goldLabel;

    @FXML
    private Button buyButtonSword, buyButtonWand, buyButtonBow, buyButtonDagger;

    @FXML
    private Button buyButtonSmallPotion, buyButtonFocusPotion, backButton;

    /**
     * Creates the shop controller.
     * This gets the scene loader and game environment so this controller
     * can use the current game data.
     *
     * @param sceneLoader the scene loader used to change scenes
     */
    public ShopController(SceneLoader sceneLoader) {
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();
    }

    /**
     * Sets up the shop scene when it opens.
     * Each buy button is connected to an item in the shop.
     * The back button goes back to the guild hall.
     */
    public void initialize() {
        buyButtonSword.setOnAction(event -> buy(0));
        buyButtonWand.setOnAction(event -> buy(1));
        buyButtonBow.setOnAction(event -> buy(2));
        buyButtonDagger.setOnAction(event -> buy(3));
        buyButtonSmallPotion.setOnAction(event -> buy(4));
        buyButtonFocusPotion.setOnAction(event -> buy(5));

        backButton.setOnAction(event -> sceneLoader.showGuildHall());
    }

    /**
     * Buys an item from the shop using its index.
     * If the player does not have enough gold, an error message is shown.
     * @param index the position of the item in the shop list
     */
    private void buy(int index) {
        Item item = gameEnvironment.shop.getAvailableItems().get(index);
        boolean bought = gameEnvironment.shop.buyItem(item, gameEnvironment);

        if (!bought) {
            GuiHelper.showError("Not enough gold to buy " + item.getName() + ".");
        }
    }
}