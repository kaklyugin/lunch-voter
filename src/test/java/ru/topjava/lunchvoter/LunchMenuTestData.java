package ru.topjava.lunchvoter;

import ru.topjava.lunchvoter.model.LunchMenu;

import java.time.LocalDate;

import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA;

public class LunchMenuTestData {
    public static final int LUNCH_MENU_SEQ_START = 1;
    public static LocalDate LUNCH_MENU_GINZA_DATE = LocalDate.of(2024, 4, 1);
    public static LocalDate NON_EXISTING_MENU_DATE = LocalDate.of(1900, 4, 1);
    
    public static final LunchMenu LUNCH_MENU_GINZA = new LunchMenu(LUNCH_MENU_SEQ_START, RESTAURANT_GINZA, LUNCH_MENU_GINZA_DATE);
    
    public static LunchMenu createNewGinzaMenuToday() {
        return new LunchMenu(null, RESTAURANT_GINZA, LocalDate.now());
    }
}
