package seng201.team0.gui;

import seng201.team0.GameEnvironment;

public class ExpeditionController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;
    public ExpeditionController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
}
