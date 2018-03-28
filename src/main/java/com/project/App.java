package com.project;

import com.project.aspect.Service;
import com.project.aspect.StatisticAspect;
import com.project.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType,EventLogger> loggers;


    public App(Client client, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.loggers = loggers;
    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public void logEvent(EventType eventType, Event event) {
        EventLogger eventLogger = loggers.get(eventType);

        if(eventLogger == null) {
            eventLogger = defaultLogger;
        }

        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"context.xml"});

        App app = (App) context.getBean("app");
        Event event = (Event) context.getBean("event");
        Event event1 = (Event) context.getBean("event");

        System.out.println(context.getBean("client"));

        app.logEvent(EventType.ERROR,event);
        app.logEvent(EventType.ERROR,event1);
        app.logEvent(null,event1);

        StatisticAspect statistic = (StatisticAspect) context.getBean("statistic");
        System.out.println(statistic.getMap());

        EventLogger eventLogger = (EventLogger) context.getBean("dbEventLogger");
        eventLogger.logEvent(new Event("some message"));
        eventLogger.logEvent(new Event("some messag321312e"));

        context.close();
    }
}
