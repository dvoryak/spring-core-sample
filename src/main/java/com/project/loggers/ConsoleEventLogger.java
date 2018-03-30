package com.project.loggers;


import com.project.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println("-----" + event);
    }
}
