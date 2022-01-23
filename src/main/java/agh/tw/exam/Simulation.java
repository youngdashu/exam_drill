package agh.tw.exam;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;


public class Simulation {

    public int questionTopicNumber;
    public Random random = new Random();

    public MainWindow mainWindow;

    public int[] topicNumbers;

    public ArrayList<Topic> topics = new ArrayList<>();

    public ArrayList<FileInputStream> inputStreams;

    public ArrayList<Image> imagesToDisplay;
    public Iterator<Image> imageIterator;

    public Image currentlyDisplayedImage;

    public Integer availablePhotosMax;
    public Integer currentPhotoNumber;

    private boolean isInOrder = true;

    private Integer currentTopicInOrder = 0;
    private Integer currentQuestionInOrder = 0;

    public Simulation(MainWindow mainWindow, String pathToQuestions, Integer numberOfSubfolders) {

        topicNumbers = IntStream.range(1, numberOfSubfolders+1).toArray();

        this.mainWindow = mainWindow;

        try {


            Arrays.stream(topicNumbers).forEach(topicNumber -> topics.add(new Topic(pathToQuestions, topicNumber)));

        } catch (RuntimeException e){
            System.out.println("Błąd w nazwie folderu!!!");
        }

        topics.sort(Topic::compareTo);

    }



    public Simulation(MainWindow mainWindow, String pathToQuestions) {
        this.mainWindow = mainWindow;


        Arrays.stream(topicNumbers).forEach(topicNumber -> topics.add(new Topic(pathToQuestions, topicNumber)));

        topics.sort(Topic::compareTo);

    }


    public Simulation(MainWindow mainWindow) {

        this(mainWindow, "./questions", 5);

    }


    public void putImageOnDisplay() {

        if (!imageIterator.hasNext()) {
            this.imageIterator = imagesToDisplay.iterator();
        }


        mainWindow.availablePhotos.setText(this.currentPhotoNumber.toString() + "/" + this.availablePhotosMax.toString());
//        System.out.println("fiswp " + fileInputStreamWithPhoto);

        currentlyDisplayedImage = imageIterator.next();

        mainWindow.photoDisplay.setImage(currentlyDisplayedImage);

        this.currentPhotoNumber++;

        if (this.currentPhotoNumber > this.availablePhotosMax) {
            this.currentPhotoNumber = 1;
        }

    }

    public void setNextQuestionChoosingMethod(boolean isInOrder) {
        this.isInOrder = isInOrder;
    }


    public void nextQuestion() {

        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");

        this.mainWindow.reactionsFromMd.setText("");

        File question;

        if(isInOrder){

            question = topics.get(currentTopicInOrder).getQuestion(currentQuestionInOrder);

            currentQuestionInOrder++;
            if(currentQuestionInOrder >= topics.get(currentTopicInOrder).questions.size()){
                currentQuestionInOrder = 0;
                currentTopicInOrder++;
                if(currentTopicInOrder >= topics.size()){
                    currentTopicInOrder = 0;
                }
            }

        } else {

            question = topics.get(random.nextInt(topics.size())).getQuestion();

        }

        System.out.println(question);

        if(question.listFiles() == null){
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            this.nextQuestion();
            return;
        }





        new MarkdownReader(question, mainWindow).read();

        FileFilter fileFilterPicture = pathname -> pathname.getName().
                matches(".*\\.(png|jpg)");

        System.out.println("----------------");

        Arrays.stream(question.listFiles()).forEach(System.out::println);

        System.out.println("!!!!!!!!!!!!!!!!!!");
        File[] photos = question.listFiles(fileFilterPicture);

        Arrays.stream(photos).forEach(System.out::println);

        String[] splitFileName = photos[0].toString().split("[/\\\\]");

        this.mainWindow.topicLabel.setText(splitFileName[2]);
        this.mainWindow.questionLabel.setText(splitFileName[3]);


        this.availablePhotosMax = photos.length;

        imagesToDisplay = new QuestionPhotosReader(photos).loadImages();

        imageIterator = imagesToDisplay.iterator();

        this.currentPhotoNumber = 1;

        this.putImageOnDisplay();


    }



    public void nextPhoto() {

        try {

            this.putImageOnDisplay();

        } catch (Exception ex){
            return;
        }

    }
}
