package ru.topjava.lunchvoter.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataJpaMenuRepository {
    Logger logger = LoggerFactory.getLogger(DataJpaMenuRepository.class);
    
    private final CrudMenuRepository crudMenuRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudDishRepository crudDishRepository;
    
    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository, CrudDishRepository crudDishRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudDishRepository = crudDishRepository;
    }
    
    @Transactional
    public Menu save(Menu menu, Integer restaurantId) {
        logger.info(String.format("Save menu=%s", menu.toString()));
        Restaurant restaurant = crudRestaurantRepository.findById(restaurantId).get(); //TODO
        if (restaurant == null) {
            logger.warn(String.format("Failed to save menu=%s. Could not find restaurant with id=%s", menu, restaurantId));
            return null;
        }
        //This fixes isse with detached dishes when POST nem menu with dish ids
        List<Integer> dishIds = menu.getDishes().stream().map(d -> d.getId()).collect(Collectors.toList());
        List<Dish> dishes = crudDishRepository.findAllById(dishIds);
        menu.clearDishes();
        dishes.forEach(d -> menu.addDish(d));
        menu.setRestaurant(restaurant);
        return crudMenuRepository.save(menu);
    }
    
    public Menu getById(Integer id) {
        logger.info(String.format("Get menu by id=%s", id));
        return crudMenuRepository.getById(id);
    }
    
    public Menu getByRestaurantAndDate(Integer restaurantId, LocalDate date) {
        logger.info(String.format("Get menu by restaurant id=%s and date=%s", restaurantId, date.toString()));
        return crudMenuRepository.getByRestaurantIdAndDate(restaurantId, date).orElse(null);
    }
    
    @Transactional
    public boolean addDish(Integer menuId, Dish dish) {
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
