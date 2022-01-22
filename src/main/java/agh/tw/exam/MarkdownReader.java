package agh.tw.exam;

import java.io.*;

public class MarkdownReader {

    FileFilter fileFilterMarkdown = pathname -> pathname.getName().
            matches(".*\\.md");

    private final File question;
    private final MainWindow mainWindow;

    public MarkdownReader(File question, MainWindow mainWindow) {
        this.question = question;
        this.mainWindow = mainWindow;
    }

    public void read() {

        File[] markdown = question.listFiles(fileFilterMarkdown);


        if (markdown != null && markdown.length != 0) {

            try {
                FileReader fileReader = new FileReader(markdown[0]);

                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String text;

                while ((text = bufferedReader.readLine()) != null) {
//                    System.out.println(text);
                    if (text.startsWith("Reactions")) {
                        mainWindow.reactionsFromMd.setText(text);
                        break;
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

}
