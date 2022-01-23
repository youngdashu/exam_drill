package agh.tw.exam;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.stream.Collectors;

public class Topic implements Comparable<Topic>{

    public String directory;

    public File folder;

    public List<File> questions;

    public Random random;

    public Topic(String directory, Integer topicNumber){

        this.random = new Random();

        this.directory = directory;
        folder = new File(directory);

        FileFilter fileFilter = pathname -> pathname.getName().startsWith(topicNumber.toString());

        File topics [] = folder.listFiles(fileFilter);

        if(topics.length == 0){
            throw new RuntimeException("empty folder");
        }

        File topic = topics[0];

        System.out.println("topic");
        System.out.println(topic);

        this.questions = new ArrayList<>();

        questions.addAll(Arrays.asList(topic.listFiles()));

        questions = questions.stream().filter(file -> ! file.getName().startsWith(".")).collect(Collectors.toList());

//        questions.forEach(file -> System.out.println(file));

        questions.sort((o1, o2) -> {


            int number1 = Integer.parseInt(o1.getName().split("-")[0]);
            int number2 = Integer.parseInt(o2.getName().split("-")[0]);

            if(number1 > number2){
                return 1;
            } else if(number1 == number2){
                return 0;
            }
            return -1;
        });


    }


    public File getQuestion(){
        File questionRes = this.questions.get(random.nextInt(this.questions.size()));

        return questionRes;

    }

    public File getQuestion(Integer i){

        return this.questions.get(i);

    }


    @Override
    public int compareTo(Topic other) {
        return this.directory.compareTo(other.directory);
    }
}
