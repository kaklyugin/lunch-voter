package ru.topjava.lunchvoter.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.model.Dish;
import ru.topjava.lunchvoter.repository.DataJpaDishRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/restaurants/", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    private final DataJpaDishRepository dataJpaDishRepository;
    
    public DishController(DataJpaDishRepository dataJpaDishRepository) {
        this.dataJpaDishRepository = dataJpaDishRepository;
    }
    
    @GetMapping("{restaurantId}/dishes")
    public List<Dish> get(@PathVariable Integer restaurantId) {
        return dataJpaDishRepository.getByRestaurantId(restaurantId);
    }
    
    @PostMapping("{restaurantId}/dishes")
    // TODO implement acceptance of both one element and array https://stackoverflow.com/questions/73732772/requestbody-that-accepts-both-list-and-single-object
    public List<Dish> saveAll(@PathVariable Long restaurantId, @RequestBody List<Dish> dishes) {
        return dataJpaDishRepository.saveAll(dishes, restaurantId);
    }
}
