package com.example.minecraftserverscontrolpanel.scene;

public enum SceneNames {
    HOME("home"),
    NEW_SERVER("newserver");

    private final String name;

    SceneNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
