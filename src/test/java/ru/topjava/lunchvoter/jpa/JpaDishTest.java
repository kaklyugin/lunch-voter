package ru.topjava.lunchvoter.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.repository.DataJpaDishRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.topjava.lunchvoter.DishTestData.getNewDishForGinza;

@SpringBootTest
@Sql(scripts = "classpath:test-data/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaDishTest {
    @Autowired
    private DataJpaDishRepository dataJpaDishRepository;
    
    @Test
    void save() {
        Dish newDish = getNewDishForGinza();
        Dish created = dataJpaDishRepository.save(newDish);
        newDish.setId(created.getId());
        assertThat(newDish).isEqualTo(created);
    }
    
    @Test
    void saveTwoDishesWithIdenticalNamesException() {
        Dish newDish = getNewDishForGinza();
        Dish created = dataJpaDishRepository.save(newDish);
        assertNotNull(created.getId());
        assertThrows(Exception.class, () -> dataJpaDishRepository.save(getNewDishForGinza()));
    }
}
