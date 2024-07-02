package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Long> {
    
    List<Dish> getByRestaurantId(Integer restaurantId);
    
}
