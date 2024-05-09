package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.model.LunchMenu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudLunchMenu extends JpaRepository<LunchMenu, Integer> {
    
    @EntityGraph(attributePaths = "restaurant", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT lm FROM LunchMenu lm WHERE lm.restaurant.id = :restaurantId AND lm.date = :date")
    Optional<LunchMenu> getByRestaurantIdAndDate(@Param("restaurantId") Integer restaurantId,
                                                 @Param("date") LocalDate date);
    
    @Query("SELECT d FROM LunchMenu lm JOIN lm.dishes d WHERE lm.restaurant.id = :restaurantId AND lm.date = :date ORDER BY d.price DESC")
    List<Dish> getDishesByRestaurantIdAndDate(@Param("restaurantId") Integer restaurantId,
                                              @Param("date") LocalDate date);
    
}
