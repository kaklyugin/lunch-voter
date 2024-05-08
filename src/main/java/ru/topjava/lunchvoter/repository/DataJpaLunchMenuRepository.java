package ru.topjava.lunchvoter.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.LunchMenu;

import java.time.LocalDate;

@Repository
public class DataJpaLunchMenuRepository {
    Logger logger = LoggerFactory.getLogger(DataJpaLunchMenuRepository.class);
    
    private final CrudLunchMenu crudLunchMenu;
    
    public DataJpaLunchMenuRepository(CrudLunchMenu crudLunchMenu) {
        this.crudLunchMenu = crudLunchMenu;
    }
    
    @Transactional
    public LunchMenu save(LunchMenu lunchMenu) {
        logger.info(String.format("Save lunch menu=%s", lunchMenu.toString()));
        return crudLunchMenu.save(lunchMenu);
    }
    
    public LunchMenu getByRestaurantAndDate(Integer restaurantId, LocalDate date) {
        logger.info(String.format("Get lunch menu by restaurant id=%s and date=%s", restaurantId, date.toString()));
        return crudLunchMenu.getByRestaurantIdAndDate(restaurantId, date).orElse(null);
    }
}
