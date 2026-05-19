package seng201.team0.gui;

import seng201.team0.GameEnvironment;

public class GuildHallController {

    GameEnvironment gameEnvironment;
    SceneLoader sceneLoader;

    public GuildHallController(SceneLoader sceneLoader){

        this.sceneLoader = sceneLoader;
        this.gameEnvironment = sceneLoader.getGameEnvironment();


    }
}
