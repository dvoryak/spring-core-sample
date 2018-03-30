package com.project.loggers;

import com.project.Event;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> events = new ArrayList<>();

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        if (events.size() <= cacheSize) {
            events.add(event);
        } else {
            writeAndClearList();
        }
    }

    private void writeAndClearList() {
        for (Event event : events) {
            super.logEvent(event);
        }

        events.clear();
    }

    @PreDestroy
    public void destroy() {
        writeAndClearList();
    }
}
