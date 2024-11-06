package com.bemo.prayertime;

import java.time.LocalDateTime;

public class DateInfo {
    private int year;
    private int month;
    private int day;

    public DateInfo(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public LocalDateTime toDateTime(int hour, int minute) {
        return LocalDateTime.of(year, month, day, hour, minute);
    }
}

