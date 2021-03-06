package agh.tw.exam;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainWindow {



    @FXML
    GridPane mainPane;

    @FXML
    VBox topicVBox;

    @FXML
    HBox buttonsHBox;

    @FXML
    VBox photoDisplayVBox;

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

    private double prevHeightPhotoDisplay;
    private double prevWidthPhotoDisplay;

    private double prevPhotoDisplayY = -1.0;

    @FXML
    ChoiceBox<String> questionOrderChoiceBox;

//    @FXML
//    GridPane gridPane;

    ObservableList<String> questionOrderList = FXCollections.observableArrayList("Losowe", "W kolejności");

    public Simulation simulation;

    public double imageRotation = 0.0;


    public void setInitData(String path, Integer numberOfSubfolders) {

        simulation = new Simulation(this, path, numberOfSubfolders);

    }


    @FXML
    public void initialize() {


        questionOrderChoiceBox.setItems(questionOrderList);
        questionOrderChoiceBox.setValue("W kolejności");

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

//        this.mainPane.setOnKeyPressed(keyEvent -> {
//            System.out.println(keyEvent.getCode());
//            if(keyEvent.getCode() == KeyCode.CONTROL){
//                prevHeightPhotoDisplay = photoDisplay.getFitHeight();
//                prevWidthPhotoDisplay = photoDisplay.getFitWidth();
//
//                photoDisplay.setFitWidth(0.95 * mainPane.getMaxWidth());
//                photoDisplay.setFitHeight(0.95 * mainPane.getMaxHeight());
//            }
//        });

    }

    @FXML
    public void prevQuestion(){

        this.simulation.prevQuestion();


    }

    @FXML
    public void nextPhoto() {

        imageRotation = 0.0;

        this.simulation.nextPhoto();

//        if(prevPhotoDisplayY != -1.0) {
//
//            this.photoDisplay.setY(prevPhotoDisplayY);
//            prevPhotoDisplayY = this.photoDisplay.getY();
//
//        }

//        VBox.setMargin(photoDisplayVBox,
//                new Insets(0,0, 0, 0)
//        );




    }

    @FXML void prevPhoto(){

//        this.simulation.prevPhoto();

    }

    @FXML
    public void rotateImage() {

        imageRotation += 90.0;

        if (imageRotation == 360.0) {
            imageRotation = 0.0;
        }

        this.photoDisplayVBox.setPadding(new Insets(topicVBox.getHeight() * 2 + buttonsHBox.getHeight() * 2,
                0, 0, 0));

        this.photoDisplay.setRotate(photoDisplay.getRotate() + 90);

        this.buttonsHBox.setViewOrder(-1.0);

//        prevPhotoDisplayY = this.photoDisplay.getY();

//        this.photoDisplay.set(prevPhotoDisplayY +
//                topicVBox.getHeight() +
//                buttonsHBox.getHeight());

//        VBox.setMargin(photoDisplayVBox,
//                new Insets(topicVBox.getHeight() * 2 + buttonsHBox.getHeight() * 2,
//                        0, 0, 500)
//        );

//        photoDisplayVBox.setPadding(new Insets(topicVBox.getHeight() * 2 + buttonsHBox.getHeight() * 2,
//                0, 0, 0));




//        photoDisplay.fitHeightProperty().bind(new DoubleBinding() {
//            @Override
//            protected double computeValue() {
//                return 750;
//            }
//        });

    }


}
