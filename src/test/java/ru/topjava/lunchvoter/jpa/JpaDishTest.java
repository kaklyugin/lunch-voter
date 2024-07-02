package ru.topjava.lunchvoter.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.repository.DataJpaDishRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.topjava.lunchvoter.DishTestData.*;
import static ru.topjava.lunchvoter.MenuTestData.LUNCH_MENU_DATE;
import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA_ID;

@SpringBootTest
@Sql(scripts = "classpath:test-data/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaDishTest {
    @Autowired
    private DataJpaDishRepository dataJpaDishRepository;
    
    @Test
    void save() {
        Dish newDish = getNewDishForGinza();
        Dish created = dataJpaDishRepository.save(getNewDishForGinza(), RESTAURANT_GINZA_ID);
        newDish.setId(created.getId());
        assertThat(newDish).isEqualTo(created);
    }
    
    @Test
    void failToSaveTwoIdenticalDishes() {
        Dish newDish = getNewDishForGinza();
        Dish created = dataJpaDishRepository.save(newDish, RESTAURANT_GINZA_ID);
        assertNotNull(created.getId());
        assertThrows(Exception.class, () -> dataJpaDishRepository.save(getNewDishForGinza(), RESTAURANT_GINZA_ID));
    }
    
    @Test
    void saveTwoSameDishesWithDifferentPrice() {
        Dish created = dataJpaDishRepository.save(getNewDishForGinza(), RESTAURANT_GINZA_ID);
        assertNotNull(created.getId());
        Dish newDishWithDifferentPrice = getNewDishForGinzaWithDifferentPrice();
        Dish createdDishWithDifferentPrice = dataJpaDishRepository.save(newDishWithDifferentPrice, RESTAURANT_GINZA_ID);
        assertNotNull(createdDishWithDifferentPrice);
    }
    
    @Test
    void getDishesByRestaurantIdAndDate() {
        List actual = dataJpaDishRepository.getDishesByRestaurantIdAndDate(RESTAURANT_GINZA_ID, LUNCH_MENU_DATE);
        List expected = List.of(
                MOTHER_IN_LAW_BORSCH_WITH_SALO,
                GREECE_SALAD,
                CAESAR_SALAD_WITH_SHRIMPS);
        Assertions.assertIterableEquals(expected, actual);
    }
}
