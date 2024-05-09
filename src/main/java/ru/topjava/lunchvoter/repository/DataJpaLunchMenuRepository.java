package ru.topjava.lunchvoter.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.model.LunchMenu;

import java.time.LocalDate;
import java.util.List;

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
    
    @Transactional
    public boolean addDish(@NonNull Integer lunchMenuId, @NonNull Dish dish) {
        logger.info(String.format("Add dish=%s to menu with id=%s", dish, lunchMenuId));
        if (lunchMenuId == null) {
            logger.warn(String.format("Add dish =%s to menu with id=%s", dish.toString(), lunchMenuId));
            return false;
        }
        if (dish.getId() != null) {
            logger.warn(String.format("Trying to add already existing (not new) dish=%s to menu with id=%s", dish, lunchMenuId));
            return false;
        }
        
        LunchMenu lunchMenu = crudLunchMenu.getReferenceById(lunchMenuId);
        lunchMenu.addDish(dish);
        return crudLunchMenu.save(lunchMenu) != null;
    }
    
    public List<Dish> getDishesByRestaurantIdAndDate(@NonNull Integer restaurantId, @NonNull LocalDate date) {
        return crudLunchMenu.getDishesByRestaurantIdAndDate(restaurantId,date);
    }
}
