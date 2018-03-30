package com.project.loggers;

import com.project.Event;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBEventLogger implements EventLogger {

    private JdbcTemplate jdbcTemplate;

    public DBEventLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void logEvent(Event event) {
        jdbcTemplate.update("insert into t_event(id,message) VALUES(?,?)",event.getId(),event.getMsg());
    }
}
