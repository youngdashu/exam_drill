package agh.tw.exam;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionPhotosReader {

    File[] photos;

    public QuestionPhotosReader(File[] photos) {
        this.photos = photos;
    }

    public ArrayList<Image> loadImages(){

        ArrayList<Image> imagesToDisplay = new ArrayList<>();
        ArrayList<FileInputStream> inputStreams = new ArrayList<>();

        Arrays.stream(photos).forEach(photoFile -> {
            try {
                inputStreams.add(new FileInputStream(photoFile.toString()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        inputStreams.forEach(fileInputStream -> imagesToDisplay.add(new Image(fileInputStream)));

        return imagesToDisplay;

    }
}
