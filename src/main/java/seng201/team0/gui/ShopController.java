package seng201.team0.gui;

import seng201.team0.GameEnvironment;

public class ShopController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;
    public ShopController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
}
