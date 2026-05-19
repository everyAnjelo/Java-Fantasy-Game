package seng201.team0.gui;

import seng201.team0.GameEnvironment;

public class GuildOverviewController {
    SceneLoader sceneLoader;
    GameEnvironment gameEnvironment;
    public GuildOverviewController(SceneLoader sceneLoader){
        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();

    }
}
