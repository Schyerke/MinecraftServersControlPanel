package com.example.minecraftserverscontrolpanel.model;

import com.example.minecraftserverscontrolpanel.file.CsvServerRecord;
import com.example.minecraftserverscontrolpanel.file.FileHelper;
import com.example.minecraftserverscontrolpanel.mcserver.McServerHelper;
import com.opencsv.exceptions.CsvRuntimeException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public enum MainModel {
    INSTANCE;
    private VBox mainVBox;
    private String previousSearch;

    public void setMainVBox(VBox mainVBox) {
        this.mainVBox = mainVBox;
    }

    public void loadAllVbox() {
        mainVBox.getChildren().clear();
        for (CsvServerRecord serverRecord : FileHelper.INSTANCE.findAll()) {
            mainVBox.getChildren().add(createServerHBox(serverRecord));
        }
    }

    private HBox createServerHBox(CsvServerRecord serverRecord) {
        HBox hBox = new HBox();
        Label label = new Label(serverRecord.serverName());
        Button deleteBtn = new Button("Delete");
        Button startBtn = new Button("Start");

        deleteBtn.setOnMouseClicked(deleteBtnEventHandler(serverRecord));
        startBtn.setOnMouseClicked(startBtnEventHandler(serverRecord));

        hBox.getChildren().addAll(label, startBtn, deleteBtn);
        return hBox;
    }

    public void loadVbox(String search) {
        mainVBox.getChildren().clear();
        CsvServerRecord serverRecord = FileHelper.INSTANCE.findServer(search);
        if(serverRecord == null){
            Label label = new Label("No server found");
            mainVBox.getChildren().add(label);
            return;
        }
        this.previousSearch = search;
        mainVBox.getChildren().add(createServerHBox(serverRecord));
        this.previousSearch = null;
    }

    private EventHandler<? super MouseEvent> startBtnEventHandler(CsvServerRecord serverRecord) {
        return mouseEvent -> McServerHelper.INSTANCE.startServer(serverRecord);
    }

    private EventHandler<? super MouseEvent> deleteBtnEventHandler(CsvServerRecord serverRecord) {
        return mouseEvent -> {
            try{
                FileHelper.INSTANCE.deleteServer(serverRecord.serverName());
                if (previousSearch != null) {
                    loadVbox(previousSearch);
                } else {
                    loadAllVbox();
                }
            }
            catch(CsvRuntimeException e){
                e.printStackTrace();
            }
        };
    }
}
