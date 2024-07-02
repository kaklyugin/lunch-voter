package ru.topjava.lunchvoter.repository;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository {
    Logger logger = LoggerFactory.getLogger(DataJpaDishRepository.class);
    
    private final CrudDishRepository crudDishRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudMenuRepository crudMenuRepository;
    
    public DataJpaDishRepository(CrudDishRepository crudDishRepository, CrudRestaurantRepository crudRestaurantRepository, CrudMenuRepository crudMenuRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudMenuRepository = crudMenuRepository;
    }
    
    public List<Dish> getByRestaurantId(Integer restaurantId) {
        logger.info(String.format("Get dishes by restaurant id=%s", restaurantId));
        return crudDishRepository.getByRestaurantId(restaurantId);
    }
    
    @Transactional
    //TODO Implement tests. Probably save method must be removed
    //TODO test that dish must not be updated as moved from one restaurant to another. It has to be deleted and inserted as the new one
    public List<Dish> saveAll(List<Dish> dish, Integer restaurantId) {
        logger.info(String.format("Save dishes=%s", dish.toString()));
        Restaurant restaurant = crudRestaurantRepository.getReferenceById(restaurantId);
        dish.forEach(d -> d.setRestaurant(restaurant));
        return crudDishRepository.saveAll(dish);
    }
    
    @Transactional
    public Dish save(Dish dish, Integer restaurantId) {
        logger.info(String.format("Save dish=%s", dish.toString()));
        Restaurant restaurant = crudRestaurantRepository.getReferenceById(restaurantId);
        dish.setRestaurant(restaurant);
        return crudDishRepository.save(dish);
    }
    
    public List<Dish> getDishesByRestaurantIdAndDate(@NonNull Integer restaurantId, @NonNull LocalDate date) {
        logger.info(String.format("Get dishes by restaurantId=%s and date=%s", restaurantId, date));
        return crudMenuRepository.getDishesByRestaurantIdAndDate(restaurantId, date);
    }
}
