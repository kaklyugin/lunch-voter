package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.lunchvoter.model.Restaurant;

@Transactional
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
