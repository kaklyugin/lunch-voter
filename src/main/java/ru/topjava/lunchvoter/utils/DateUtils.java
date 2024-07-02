package ru.topjava.lunchvoter.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateUtils {
    private static final LocalTime CUT_OFF_TIME = LocalTime.parse("20:00:00");
    
    public static final LocalDate getActualMenuDate() {
        var nowTime = LocalTime.now();
        if (nowTime.isBefore(CUT_OFF_TIME)) {
            return LocalDate.now();
        }
        return LocalDate.now().plusDays(1);
    }
}
