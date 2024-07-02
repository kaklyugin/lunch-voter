package ru.topjava.lunchvoter.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.model.Restaurant;
import ru.topjava.lunchvoter.repository.DataJpaRestaurantRepository;

@RestController
@RequestMapping(value = "/admin/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private final DataJpaRestaurantRepository dataJpaRestaurantRepository;
    
    public RestaurantController(DataJpaRestaurantRepository dataJpaRestaurantRepository) {
        this.dataJpaRestaurantRepository = dataJpaRestaurantRepository;
    }
    
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable Long id) {
        return dataJpaRestaurantRepository.get(id);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant save(@RequestBody Restaurant restaurant) {
        return dataJpaRestaurantRepository.save(restaurant);
    }
    
}
