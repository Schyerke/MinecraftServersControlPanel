package com.example.minecraftserverscontrolpanel.controller;

import com.example.minecraftserverscontrolpanel.model.MainModel;
import com.example.minecraftserverscontrolpanel.scene.SceneHelper;
import com.example.minecraftserverscontrolpanel.scene.SceneNames;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    public Button addNewServerButton;
    @FXML
    private ScrollPane mainScrollpane;
    @FXML
    private VBox mainVBox;
    @FXML
    private Button searchbarSubmit;
    @FXML
    private TextField searchbar;

    @FXML
    public void initialize() {
        MainModel.INSTANCE.setMainVBox(mainVBox);
        MainModel.INSTANCE.loadAllVbox();

    }

    @FXML
    public void onSearchbarSubmitClick(MouseEvent mouseEvent) {
        String search = searchbar.getText();
        loadVBox(search);
    }

    @FXML
    public void onSearchbarKeypressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() != KeyCode.ENTER){
            return;
        }
        String search = searchbar.getText();
        loadVBox(search);
    }

    @FXML
    public void onAddNewServerBtnClicked(MouseEvent mouseEvent) {
        SceneHelper.INSTANCE.activate(SceneNames.NEW_SERVER.getName());
    }

    private void loadVBox(String search) {
        MainModel.INSTANCE.loadVbox(search);
    }

    public VBox getMainVBox() {
        return mainVBox;
    }
}