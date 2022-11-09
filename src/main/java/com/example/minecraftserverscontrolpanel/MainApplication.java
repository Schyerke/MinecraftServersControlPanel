package com.example.minecraftserverscontrolpanel;

import com.example.minecraftserverscontrolpanel.discord.DiscordBot;
import com.example.minecraftserverscontrolpanel.scene.SceneHelper;
import com.example.minecraftserverscontrolpanel.scene.SceneNames;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static MainApplication instance;
    public MainApplication(){
        instance = this;
    }

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("root.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        System.out.println("Starting application");
        SceneHelper.INSTANCE.setMainScene(scene);
        sceneHelperInit();
        stage.setTitle("Minecraft Servers Control Panel");
        stage.setScene(scene);
        stage.show();
        SceneHelper.INSTANCE.activate(SceneNames.HOME.getName());
    }

    private void sceneHelperInit() {
        try{
            SceneHelper.INSTANCE.addScreen(
                    SceneNames.HOME.getName(),
                    new FXMLLoader(MainApplication.class.getResource("home.fxml")).load()
            );

            SceneHelper.INSTANCE.addScreen(
                    SceneNames.NEW_SERVER.getName(),
                    new FXMLLoader(MainApplication.class.getResource("newserver.fxml")).load()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}