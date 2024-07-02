package ru.topjava.lunchvoter;

import ru.topjava.lunchvoter.model.Restaurant;

public class RestaurantTestData {
    public static final Long RESTAURANT_SEQ_START = 1L;
    public static final Long RESTAURANT_GINZA_ID = 1L;
    public static final Long RESTAURANT_MAMA_ITALIA_ID = RESTAURANT_SEQ_START + 1;
    
    public static final Restaurant RESTAURANT_GINZA = new Restaurant(RESTAURANT_GINZA_ID, "Гинза");
    public static final Restaurant RESTAURANT_MAMA_ITALIA = new Restaurant(RESTAURANT_MAMA_ITALIA_ID, "Мама Италия");
}
