package ru.topjava.lunchvoter;

import ru.topjava.lunchvoter.model.Dish;

import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA;

public class DishTestData {
    public static final String NEW_DISH_NAME = "Овсянка";
    
    public static final Dish getNewDishForGinza() {
        return new Dish(null, NEW_DISH_NAME, 299.0f, RESTAURANT_GINZA);
    }
}
