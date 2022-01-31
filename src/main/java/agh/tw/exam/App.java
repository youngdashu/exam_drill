package agh.tw.exam;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));

        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();
        root.requestFocus();
    }


    public static void main(String[] args) {



        launch(args);
    }



}