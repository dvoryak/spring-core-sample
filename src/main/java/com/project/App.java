package com.project;

import com.project.aspect.StatisticAspect;
import com.project.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        /*ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"context.xml"});*/

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);


        App app = (App) context.getBean("app");
        app.logEvent(EventType.ERROR,new Event("some message"));
        app.logEvent(EventType.INFO,new Event("some message"));
        app.logEvent(EventType.INFO,new Event("some message"));
        app.logEvent(EventType.ERROR,new Event("some message"));
        app.logEvent(EventType.ERROR,new Event("some message"));

        //Statistic aspect
        StatisticAspect statistic = (StatisticAspect) context.getBean("statistic");
        statistic.getMap().forEach((aClass, integer) -> System.out.println(aClass.getSimpleName() + " = " + integer));

        //DataBase check
        /*EventLogger eventLogger = (EventLogger) context.getBean("dbEventLogger");
        eventLogger.logEvent(new Event("some message"));
        eventLogger.logEvent(new Event("some messag321312e"));*/

        //Proxy
        AppContext appContext = new AppContext();
        AppContext appContext1 = context.getBean(AppContext.class);
        System.out.println(appContext.getClass() + " vs " + appContext1.getClass());


        context.close();
    }
}
