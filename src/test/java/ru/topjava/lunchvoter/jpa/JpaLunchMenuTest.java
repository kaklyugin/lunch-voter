package ru.topjava.lunchvoter.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.topjava.lunchvoter.model.LunchMenu;
import ru.topjava.lunchvoter.repository.DataJpaLunchMenuRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.topjava.lunchvoter.LunchMenuTestData.*;
import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA_ID;

@SpringBootTest
@Sql(scripts = "classpath:test-data/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaLunchMenuTest {
    @Autowired
    private DataJpaLunchMenuRepository dataJpaLunchMenuRepository;
    
    @Test
    void save() {
        LunchMenu newLunchMenu = createNewGinzaMenuToday();
        LunchMenu created = dataJpaLunchMenuRepository.save(newLunchMenu);
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
}
