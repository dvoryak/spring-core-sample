package com.project;

import sun.util.resources.LocaleData;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Event {

    private int id;
    private String msg;
    private Date date;
    private DateFormat dt;

    public Event(String msg) {
        this.date = new Date();
        this.id = new Random().nextInt();
        this.msg = msg;
    }

    public Event(Date date, DateFormat dt) {
        id = new Random().nextInt();
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

    public static boolean isDay() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        return hours > 8 && hours < 17;
    }

    @Override
    public String toString() {
        return "Event#" + id +
                " message='" + msg + '\'' +
                " date=" + dt.format(date);
    }
}
