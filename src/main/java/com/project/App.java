package com.project;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event) {
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"context.xml"});

        App app = (App) context.getBean("app");
        Event event = (Event) context.getBean("event");
        Event event1 = (Event) context.getBean("event");

        app.logEvent(event);
        app.logEvent(event1);

        context.close();
    }
}
