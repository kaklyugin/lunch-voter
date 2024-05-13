package ru.topjava.lunchvoter.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.repository.DataJpaMenuRepository;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    private final DataJpaMenuRepository dataJpaMenuRepository;
    
    public MenuController(DataJpaMenuRepository dataJpaMenuRepository) {
        this.dataJpaMenuRepository = dataJpaMenuRepository;
    }
    
    @GetMapping("/restaurants/{restaurantId}/menus")
    public Menu get(@PathVariable Integer restaurantId, @RequestParam LocalDate date) {
        return dataJpaMenuRepository.getByRestaurantAndDate(restaurantId, date);
    }
    
    @GetMapping("/menus/{id}")
    public Menu get(@PathVariable Integer id) {
        return dataJpaMenuRepository.getById(id);
    }
    
    @PostMapping(value = "/restaurants/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu save(@PathVariable Integer restaurantId, @RequestBody Menu menu) {
        return dataJpaMenuRepository.save(menu, restaurantId);
    }
    
}
