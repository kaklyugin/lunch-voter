package ru.topjava.lunchvoter;

import ru.topjava.lunchvoter.model.Dish;

import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA;

public class DishTestData {
    public static final String NEW_DISH_NAME = "Овсянка";
    
    public static final Dish getNewDishForGinza() {
        return new Dish(null, NEW_DISH_NAME, 299.0f, RESTAURANT_GINZA);
    }
    
    public static final Dish getNewDishForGinzaWithDifferentPrice() {
        return new Dish(null, NEW_DISH_NAME, 300.0f, RESTAURANT_GINZA);
    }
    
    public static final Dish CAESAR_SALAD_WITH_SHRIMPS = new Dish(1, "Цезарь с креветками", 100.0f, RESTAURANT_GINZA);
    public static final Dish GREECE_SALAD = new Dish(2, "Греческий салат", 200.0f, RESTAURANT_GINZA);
    public static final Dish MOTHER_IN_LAW_BORSCH_WITH_SALO = new Dish(3, "Тёщин борщ с сальцом", 350.0f, RESTAURANT_GINZA);
}
