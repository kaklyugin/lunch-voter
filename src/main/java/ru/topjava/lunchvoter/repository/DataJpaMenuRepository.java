package ru.topjava.lunchvoter.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMenuRepository {
    Logger logger = LoggerFactory.getLogger(DataJpaMenuRepository.class);
    
    private final CrudMenuRepository crudMenuRepository;
    
    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository) {
        this.crudMenuRepository = crudMenuRepository;
    }
    
    @Transactional
    public Menu save(Menu menu) {
        logger.info(String.format("Save menu=%s", menu.toString()));
        return crudMenuRepository.save(menu);
    }
    
    public Menu getByRestaurantAndDate(Integer restaurantId, LocalDate date) {
        logger.info(String.format("Get menu by restaurant id=%s and date=%s", restaurantId, date.toString()));
        return crudMenuRepository.getByRestaurantIdAndDate(restaurantId, date).orElse(null);
    }
    
    @Transactional
    public boolean addDish(@NonNull Integer menuId, @NonNull Dish dish) {
        logger.info(String.format("Add dish=%s to menu with id=%s", dish, menuId));
        if (menuId == null) {
            logger.warn(String.format("Add dish =%s to menu with id=%s", dish.toString(), menuId));
            return false;
        }
        if (dish.getId() != null) {
            logger.warn(String.format("Trying to add already existing (not new) dish=%s to menu with id=%s", dish, menuId));
            return false;
        }
        
        Menu menu = crudMenuRepository.getReferenceById(menuId);
        menu.addDish(dish);
        return crudMenuRepository.save(menu) != null;
    }
    
    public List<Dish> getDishesByRestaurantIdAndDate(@NonNull Integer restaurantId, @NonNull LocalDate date) {
        return crudMenuRepository.getDishesByRestaurantIdAndDate(restaurantId, date);
    }
}
