package ru.topjava.lunchvoter.repository;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.topjava.lunchvoter.model.Dish;

@Repository
public class DataJpaDishRepository {
    Logger logger = LoggerFactory.getLogger(DataJpaDishRepository.class);
    
    private final CrudDishRepository crudDishRepository;
    
    public DataJpaDishRepository(CrudDishRepository crudDishRepository) {
        this.crudDishRepository = crudDishRepository;
    }
    
    @Transactional
    public Dish save(Dish dish) {
        return crudDishRepository.save(dish);
    }
}
