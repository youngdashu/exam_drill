package agh.tw.exam;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenu {


    @FXML
    private Button startDrillButton;

    @FXML
    private TextField pathToQuestionsTextField;

    @FXML
    private TextField numberOfSubfolders;

    public void initialize() {


        numberOfSubfolders.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberOfSubfolders.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


    }

    @FXML
    public void openDrill() throws IOException {


        String path = this.pathToQuestionsTextField.getText();
        Integer numberOfSubfolders = Integer.valueOf(this.numberOfSubfolders.getText());

        Stage mainWindowStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = (Parent) loader.load();

        MainWindow mainWindow = loader.getController();

        mainWindow.setInitData(path, numberOfSubfolders);

        mainWindowStage.setScene(new Scene(root));
        mainWindowStage.show();

        ((Stage) startDrillButton.getScene().getWindow()).close();


    }


}
