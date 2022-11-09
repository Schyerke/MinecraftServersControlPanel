package com.example.minecraftserverscontrolpanel.scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

/**
 * @author stefa
 */

@SuppressWarnings("ALL")
public enum SceneHelper {
    INSTANCE;

    private final HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene mainScene;

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        mainScene.setRoot( screenMap.get(name) );
    }

}
