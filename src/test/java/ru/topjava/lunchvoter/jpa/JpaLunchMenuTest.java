package ru.topjava.lunchvoter.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.model.LunchMenu;
import ru.topjava.lunchvoter.repository.DataJpaLunchMenuRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.topjava.lunchvoter.DishTestData.*;
import static ru.topjava.lunchvoter.LunchMenuTestData.*;
import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA_ID;

@SpringBootTest
@Sql(scripts = "classpath:test-data/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaLunchMenuTest {
    @Autowired
    private DataJpaLunchMenuRepository dataJpaLunchMenuRepository;
    
    @Test
    void save() {
        LunchMenu created = dataJpaLunchMenuRepository.save(createNewGinzaMenuToday());
        LunchMenu newLunchMenu = createNewGinzaMenuToday();
        newLunchMenu.setId(created.getId());
        assertThat(newLunchMenu).isEqualTo(created);
    }
    
    @Test
    void saveTwoMenusAtSameDateError() {
        dataJpaLunchMenuRepository.save(createNewGinzaMenuToday());
        assertThrows(Exception.class, () -> dataJpaLunchMenuRepository.save(createNewGinzaMenuToday()));
    }
    
    @Test
    void getByRestaurantIdAndDate() {
        LunchMenu actual = dataJpaLunchMenuRepository.getByRestaurantAndDate(RESTAURANT_GINZA_ID, LUNCH_MENU_GINZA_DATE);
        assertThat(actual).isEqualTo(LUNCH_MENU_GINZA);
    }
    
    @Test
    void getByRestaurantIdAtNotExistingMenuDate() {
        LunchMenu actual = dataJpaLunchMenuRepository.getByRestaurantAndDate(RESTAURANT_GINZA_ID, NON_EXISTING_MENU_DATE);
        assertThat(actual).isNull();
    }
    
    @Test
    void addDish() {
        Dish newDish = getNewDishForGinza();
        assertTrue(dataJpaLunchMenuRepository.addDish(LUNCH_MENU_GINZA_ID, newDish));
    }
    
    @Test
    void getDishesByRestaurantIdAndDate() {
        List actual = dataJpaLunchMenuRepository.getDishesByRestaurantIdAndDate(RESTAURANT_GINZA_ID, LUNCH_MENU_GINZA_DATE);
        List expected = List.of(
                MOTHER_IN_LAW_BORSCH_WITH_SALO,
                GREECE_SALAD,
                CAESAR_SALAD_WITH_SHRIMPS);
        Assertions.assertIterableEquals(expected, actual);
    }
    
    @Test
    void addDishAndCheckBelonging() {
        List<Dish> dishes = dataJpaLunchMenuRepository.getDishesByRestaurantIdAndDate(RESTAURANT_GINZA_ID, LUNCH_MENU_GINZA_DATE);
        final int dishesCountInMenu = dishes.size();
        assertTrue(dataJpaLunchMenuRepository.addDish(LUNCH_MENU_GINZA_ID, getNewDishForGinza()));
        dishes = dataJpaLunchMenuRepository.getDishesByRestaurantIdAndDate(RESTAURANT_GINZA_ID, LUNCH_MENU_GINZA_DATE);
        assertThat(dishes.size()).isEqualTo(dishesCountInMenu + 1);
        Dish newDish = getNewDishForGinza();
        newDish.setId(dishes.get(1).getId());
        assertThat(newDish).isEqualTo(dishes.get(1));
    }
}
