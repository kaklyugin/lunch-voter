package ru.topjava.lunchvoter;

import ru.topjava.lunchvoter.model.Menu;

import java.time.LocalDate;

import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA;

public class MenuTestData {
    public static final Long LUNCH_MENU_SEQ_START = 1L;
    public static final Long LUNCH_MENU_GINZA_ID = LUNCH_MENU_SEQ_START;
    public static final Long LUNCH_MENU_MAMA_ITALIA_ID = LUNCH_MENU_SEQ_START + 1;
    public static LocalDate LUNCH_MENU_DATE = LocalDate.of(2024, 4, 1);
    public static LocalDate NON_EXISTING_MENU_DATE = LocalDate.of(1900, 4, 1);
    public static LocalDate LUNCH_MENU_NEW_DATE = LocalDate.of(2055, 11, 12);
    
    public static final Menu LUNCH_MENU_GINZA = new Menu(LUNCH_MENU_SEQ_START, RESTAURANT_GINZA, LUNCH_MENU_DATE);
    
    public static Menu createNewGinzaMenuNewDay() {
        return new Menu(null, RESTAURANT_GINZA, LUNCH_MENU_NEW_DATE);
    }
}
