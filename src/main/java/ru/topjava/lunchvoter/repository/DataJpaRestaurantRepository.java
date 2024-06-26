package ru.topjava.lunchvoter.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvoter.model.Restaurant;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository {
    Logger logger = LoggerFactory.getLogger(DataJpaRestaurantRepository.class);
    
    private final CrudRestaurantRepository crudRestaurantRepository;
    
    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }
    
    public Restaurant get(int id) {
        logger.info(String.format("Get restaurant by id=%s", id));
        return crudRestaurantRepository.findById(id).orElse(null);
    }
    
    public List<Restaurant> getAll() {
        logger.info("Get all restaurants");
        return crudRestaurantRepository.findByOrderById();
    }
    
    public Restaurant save(Restaurant restaurant) {
        logger.info(String.format("Save restaurant=%s", restaurant));
        return crudRestaurantRepository.save(restaurant);
    }
}
