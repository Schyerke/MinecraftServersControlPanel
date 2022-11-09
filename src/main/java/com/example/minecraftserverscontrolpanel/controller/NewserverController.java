package com.example.minecraftserverscontrolpanel.controller;

import com.example.minecraftserverscontrolpanel.file.FileHelper;
import com.example.minecraftserverscontrolpanel.model.MainModel;
import com.example.minecraftserverscontrolpanel.scene.SceneHelper;
import com.example.minecraftserverscontrolpanel.scene.SceneNames;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class NewserverController {
    private static final String NO_DIRECTORY_SELECTED = "No Directory selected";
    public static final String INVALID_FILE_SELECTED = "Invalid file selected";
    @FXML public Text filepathText;
    @FXML public Button selectMcServerBtn;
    @FXML public ImageView homePageBtn;
    @FXML public AnchorPane homePageBtnAnchor;
    @FXML public Button addBtn;
    @FXML public TextField serverNameText;

    @FXML
    public void onHomePageBtnClicked(MouseEvent mouseEvent) {
        SceneHelper.INSTANCE.activate(SceneNames.HOME.getName());
        MainModel.INSTANCE.loadAllVbox();
    }

    @FXML
    public void onSelectMcServerBtnClicked(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Jar Files", "*.jar")
        );

        File selectedFile = fileChooser.showOpenDialog(SceneHelper.INSTANCE.getMainScene().getWindow());

        if(selectedFile == null){
            filepathText.setText(NO_DIRECTORY_SELECTED);
            return;
        }
        filepathText.setText(selectedFile.getAbsolutePath());
    }

    @FXML
    public void onAddBtnClicked(MouseEvent mouseEvent) {
        if(filepathText.getText().equals(NO_DIRECTORY_SELECTED)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(NO_DIRECTORY_SELECTED);
            alert.setContentText("Please select a directory");
            alert.show();
            return;
        }
        if(filepathText.getText().equals(INVALID_FILE_SELECTED)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(INVALID_FILE_SELECTED);
            alert.setContentText("Please select a valid file");
            alert.show();
            return;
        }
        if(serverNameText.getText().equals("") || serverNameText == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Server Name");
            alert.setContentText("Please enter a server name");
            alert.show();
            return;
        }
        FileHelper.INSTANCE.saveServer(serverNameText.getText(), filepathText.getText().replace("\\", "/"));
        System.out.println(filepathText.getText().replace("\\", "/"));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Server added");
        alert.setContentText("Server added successfully");
        alert.setContentText("File path: " + filepathText.getText());
        alert.show();
    }
}
