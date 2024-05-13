package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.model.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    //TODO need to add test for this method
    @EntityGraph(attributePaths = "restaurant", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT lm FROM Menu lm JOIN FETCH  lm.dishes d WHERE lm.restaurant.id =:restaurantId and lm.id = :id")
    Menu getByRestaurantIdAndId(@Param("restaurantId") Integer restaurantId, @Param("id") Integer id);
    
    @EntityGraph(attributePaths = "restaurant", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT lm FROM Menu lm WHERE lm.restaurant.id = :restaurantId AND lm.date = :date")
    Optional<Menu> getByRestaurantIdAndDate(@Param("restaurantId") Integer restaurantId,
                                            @Param("date") LocalDate date);
    
    @Query("SELECT d FROM Menu lm JOIN lm.dishes d WHERE lm.restaurant.id = :restaurantId AND lm.date = :date ORDER BY d.price DESC")
    List<Dish> getDishesByRestaurantIdAndDate(@Param("restaurantId") Integer restaurantId,
                                              @Param("date") LocalDate date);
    
}
