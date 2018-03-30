package com.project;

import com.project.loggers.*;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.project.aspect")
public class AppContext {

    @Bean
    BeanPostProcessor autowiredBeanPostProcessor() {
        return new AutowiredAnnotationBeanPostProcessor();
    }

    //@Scope("prototype")
    @Bean
    EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    EventLogger fileEventLogger() {
        return new FileEventLogger("file.txt");
    }

    @Bean
    EventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("file.txt", 100);
    }

    @Bean
    PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc
                = new PropertyPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]
                {new ClassPathResource("database.properties"), new ClassPathResource("client.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    @Bean
    DataSource dataSource(
            @Value("${mySql.driver}") String driverClass,
            @Value("${mySql.url}") String jdbcUrl,
            @Value("${mySql.user}") String user,
            @Value("${mySql.password}") String password) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setUrl(jdbcUrl);
        driverManagerDataSource.setUsername(user);
        driverManagerDataSource.setPassword(password);
        driverManagerDataSource.setDriverClassName(driverClass);
        return driverManagerDataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    EventLogger dbEventLogger(JdbcTemplate jdbcTemplate) {
        return new DBEventLogger(jdbcTemplate);
    }

    @Bean
    List<EventLogger> loggerList() {
        List<EventLogger> list = new ArrayList<>();
        list.add(fileEventLogger());
        list.add(consoleEventLogger());
        return list;
    }

    @Bean
    EventLogger combinedEventLogger() {
        return new CombinedEventLogger(loggerList());
    }

    @Bean
    Map<EventType, EventLogger> loggersMap() {
        Map<EventType, EventLogger> map = new HashMap<>();
        map.put(EventType.INFO, consoleEventLogger());
        map.put(EventType.ERROR, combinedEventLogger());
        return map;
    }

    @Bean
    Client client() {
       //System.out.println(fileEventLogger() == fileEventLogger());
        return new Client();
    }

    @Bean
    App app() {
        return new App(client(), consoleEventLogger(), loggersMap());
    }

}
