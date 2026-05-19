package seng201.team0.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng201.team0.GameEnvironment;

import java.io.IOException;

public class SceneLoader {
    private final Stage stage;
    private final GameEnvironment gameEnvironment;

    public SceneLoader(Stage stage) {
        this.stage = stage;
        this.gameEnvironment = new GameEnvironment();
    }

    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    public void showStartUp() {
        load("/fxml/StartUpScene_final.fxml", new StartUpController(this));
    }

    public void showGuildHall() {
        load("/fxml/GuildHallScene.fxml", new GuildHallController(this));
    }
/*
    public void showGuildOverview() {
        load("/fxml/GuildOverviewScene_final.fxml", new GuildOverviewController(this));
    }

    public void showShop() {
        load("/fxml/ShopScene_final.fxml", new ShopController(this));
    }

    public void showInventory() {
        load("/fxml/InventoryScene.fxml", new InventoryController(this));
    }

    public void showExpedition() {
        load("/fxml/ExpeditionScene.fxml", new ExpeditionController(this));
    }

    public void showResult() {
        load("/fxml/ResultScene.fxml", new ResultController(this));
    }
    public void showExpeditionSelection() {
        load("/fxml/ExpeditionScene.fxml", new EventController(this));
    }
*/
    private void load(String fxmlPath, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setController(controller);

            Parent root = loader.load();

            stage.setScene(new Scene(root, 635, 655));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Could not load " + fxmlPath, e);
        }
    }
}