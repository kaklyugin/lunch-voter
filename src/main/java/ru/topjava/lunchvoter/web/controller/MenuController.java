package ru.topjava.lunchvoter.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.model.Menu;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController extends AbstractMenuController {

//    @GetMapping("/restaurants/{restaurantId}/menus")
//    public Menu getByRestaurantIdAndDate(@PathVariable Integer restaurantId, @RequestParam LocalDate date) {
//        return dataJpaMenuRepository.getByRestaurantAndDate(restaurantId, date);
//    }
    
    @Override
    @GetMapping("/menus")
    public List<Menu> getByDate(@RequestParam LocalDate date) {
        return super.getByDate(date);
    }
    
    @Override
    @PostMapping(value = "/restaurants/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu save(@PathVariable Integer restaurantId, @RequestBody Menu menu) {
        return super.save(restaurantId, menu);
    }
    
}
