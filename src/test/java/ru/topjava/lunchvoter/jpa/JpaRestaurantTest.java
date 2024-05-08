package ru.topjava.lunchvoter.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.topjava.lunchvoter.model.Restaurant;
import ru.topjava.lunchvoter.repository.DataJpaRestaurantRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA;
import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_GINZA_ID;
import static ru.topjava.lunchvoter.RestaurantTestData.RESTAURANT_MAMA_ITALIA;

@SpringBootTest
//TODO сделать профили для тестов и для прода, чтобы можно было запускать разные скрипты с начальными данными м.б. использовать параметр для запуска скрипта после теста
//TODO выводить ожидаемые и фактические данные в консоль
@Sql(scripts = "classpath:test-data/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaRestaurantTest {
    @Autowired
    private DataJpaRestaurantRepository dataJpaRestaurantRepository;
    
    @Test
    void getById() {
        Restaurant actual = dataJpaRestaurantRepository.get(RESTAURANT_GINZA_ID);
        assertThat(actual).isEqualTo(RESTAURANT_GINZA);
    }
    
    @Test
    void getAll() {
        List actual = dataJpaRestaurantRepository.getAll();
        List expected = List.of(RESTAURANT_GINZA, RESTAURANT_MAMA_ITALIA
        );
        Assertions.assertIterableEquals(expected, actual);
    }
}
