package com.project;

import java.text.DateFormat;
import java.util.Date;

public class Event {

    private static int id = 0;
    private String msg;
    private Date date;
    private DateFormat dt;

    public Event() {
        id++;
    }

    public Event(Date date, DateFormat dt) {
        this();
        this.date = date;
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event#" + id +
                " message='" + msg + '\'' +
                " date=" + dt.format(date);
    }
}
