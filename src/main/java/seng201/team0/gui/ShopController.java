package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;
import seng201.team0.Item;

public class ShopController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;
    @FXML
    private Label priceSword, priceWand, priceBow, priceDagger, priceSmallPotion, priceFocusPotion, goldLabel;
    @FXML private Button buyButtonSword, buyButtonWand, buyButtonBow, buyButtonDagger, buyButtonSmallPotion, buyButtonFocusPotion, backButton;
    public ShopController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
    public void initialize() {
        buyButtonSword.setOnAction(event -> buy(0));
        buyButtonWand.setOnAction(event -> buy(1));
        buyButtonBow.setOnAction(event -> buy(2));
        buyButtonDagger.setOnAction(event -> buy(3));
        buyButtonSmallPotion.setOnAction(event -> buy(4));
        buyButtonFocusPotion.setOnAction(event -> buy(5));
        backButton.setOnAction(event -> sceneLoader.showGuildHall());
    }



    private void buy(int index) {
        Item item = gameEnvironment.shop.getAvailableItems().get(index);
        boolean bought = gameEnvironment.shop.buyItem(item, gameEnvironment);
        if (!bought) {
            GuiHelper.showError("Not enough gold to buy " + item.getName() + ".");
        }
    }
}

