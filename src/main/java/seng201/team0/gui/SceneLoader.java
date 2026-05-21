package seng201.team0.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng201.team0.gameEnvironment.GameEnvironment;

import java.io.IOException;

/**
 * This class is used to load and switch between different scenes in the game.
 *
 * It also stores one GameEnvironment object so all controllers can use
 * the same game data instead of creating a new game every time.
 */
public class SceneLoader {

    private final Stage stage;
    private final GameEnvironment gameEnvironment;

    /**
     * Creates a new SceneLoader.
     *
     * This also creates one GameEnvironment for the whole game.
     *
     * @param stage the main stage/window of the game
     */
    public SceneLoader(Stage stage) {
        this.stage = stage;
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Gets the game environment.
     *
     * This lets the controllers access the same game data.
     *
     * @return the game environment used by the game
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * Shows the startup scene.
     */
    public void showStartUp() {
        load("/fxml/StartUpScene_final.fxml", new StartUpController(this));
    }

    /**
     * Shows the guild hall scene.
     */
    public void showGuildHall() {
        load("/fxml/GuildHallScene.fxml", new GuildHallController(this));
    }

    /**
     * Shows the guild overview scene.
     */
    public void showGuildOverview() {
        load("/fxml/GuildOverviewScene_final.fxml", new GuildOverviewController(this));
    }

    /**
     * Shows the shop scene.
     */
    public void showShop() {
        load("/fxml/ShopScene_final.fxml", new ShopController(this));
    }

    /**
     * Shows the inventory scene.
     */
    public void showInventory() {
        load("/fxml/InventoryScene.fxml", new InventoryController(this));
    }

    /**
     * Shows the expedition gameplay scene.
     */
    public void showExpedition() {
        load("/fxml/ExpeditionScene.fxml", new ExpeditionController(this));
    }

    /**
     * Shows the result scene.
     */
    public void showResult() {
        load("/fxml/ResultScene.fxml", new ResultController(this));
    }

    /**
     * Shows the expedition selection scene.
     */
    public void showExpeditionSelection() {
        load("/fxml/ExpeditionSelectionScene.fxml", new ExpeditionSelectionController(this));
    }

    /**
     * Loads an FXML file and shows it on the stage.
     *
     * The controller is also set here before the FXML is loaded.
     *
     * @param fxmlPath the path of the FXML file
     * @param controller the controller used for the scene
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