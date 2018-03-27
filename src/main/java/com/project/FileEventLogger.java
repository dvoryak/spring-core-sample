package com.project;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        file = new File(fileName);
    }


    public void logEvent(Event event) {

        try {
            FileUtils.writeStringToFile(file, System.getProperty("line.separator") + event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
