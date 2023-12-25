package com.example.weather24;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Day {
    String name;
    double minTemperature;
    double maxTemperature;

    public static Date parseToDate(String dateInString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return formatter.parse(dateInString);
    }

    public static String getDayString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return formatter.format(date);
    }

    public static String getDayOfWeekString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        return formatter.format(date);
    }

    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    public Day(Date data, double minTemperature, double maxTemperature) {
        this.name = getDayOfWeekString(data);
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    @Override
    public String toString() {
        return name + ": Min temp " + minTemperature + "°C" + " Max temp " + maxTemperature + "°C";
    }
}
