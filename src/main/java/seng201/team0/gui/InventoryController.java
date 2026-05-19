package seng201.team0.gui;

import seng201.team0.GameEnvironment;

public class InventoryController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;
    public InventoryController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
}
