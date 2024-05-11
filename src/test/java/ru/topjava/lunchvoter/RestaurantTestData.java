package ru.topjava.lunchvoter;

import ru.topjava.lunchvoter.model.Restaurant;

public class RestaurantTestData {
    public static final int RESTAURANT_SEQ_START = 1;
    public static final int RESTAURANT_GINZA_ID = 1;
    public static final int RESTAURANT_MAMA_ITALIA_ID = RESTAURANT_SEQ_START + 1;
    
    public static final Restaurant RESTAURANT_GINZA = new Restaurant(RESTAURANT_GINZA_ID, "Гинза");
    public static final Restaurant RESTAURANT_MAMA_ITALIA = new Restaurant(RESTAURANT_MAMA_ITALIA_ID, "Мама Италия");
}
