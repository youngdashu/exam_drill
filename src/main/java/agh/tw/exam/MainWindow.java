package agh.tw.exam;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainWindow {

    @FXML
    Label questionLabel;

    @FXML
    Label topicLabel;

    @FXML
    Label availablePhotos;

    @FXML
    Label reactionsFromMd;

    @FXML
    Button nextPhotoButton;

    @FXML
    Button nextQuestionButton;

//    @FXML
//    Button rotateImageButton;

    @FXML
    ImageView photoDisplay;

    @FXML
    ChoiceBox<String> questionOrderChoiceBox;

//    @FXML
//    GridPane gridPane;

    ObservableList<String> questionOrderList = FXCollections.observableArrayList("Losowe", "W kolejności");

    public Simulation simulation;

    public double imageRotation = 0.0;

//    private String path;
//    private Integer numberOfSubfolders;

    public void setInitData(String path, Integer numberOfSubfolders) {

//        this.path = path;
//        this.numberOfSubfolders = numberOfSubfolders;

        simulation = new Simulation(this, path, numberOfSubfolders);

    }


    @FXML
    public void initialize() {


        questionOrderChoiceBox.setItems(questionOrderList);
        questionOrderChoiceBox.setValue("Losowe");

        questionOrderChoiceBox.getSelectionModel().selectedIndexProperty().
                addListener(
                        (observableValue, number, newValue) ->
                                simulation.setNextQuestionChoosingMethod(newValue.intValue() == 1)
                );

//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }

    @FXML
    public void nextQuestion() {

        imageRotation = 0.0;

        this.simulation.nextQuestion();

    }

    @FXML
    public void nextPhoto() {

        imageRotation = 0.0;

        this.simulation.nextPhoto();

    }

//    @FXML
//    public void rotateImage() {
//
//        imageRotation += 90.0;
//
//        if (imageRotation == 360.0) {
//            imageRotation = 0.0;
//        }
//
//        this.photoDisplay.setRotate(imageRotation);
//
//        photoDisplay.fitHeightProperty().bind(new DoubleBinding() {
//            @Override
//            protected double computeValue() {
//                return 750;
//            }
//        });
//
//    }


}
