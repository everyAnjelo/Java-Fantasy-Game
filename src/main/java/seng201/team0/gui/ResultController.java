package seng201.team0.gui;

import seng201.team0.GameEnvironment;

public class ResultController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;
    public ResultController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
}
