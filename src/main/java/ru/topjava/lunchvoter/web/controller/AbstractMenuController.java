package ru.topjava.lunchvoter.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.service.MenuService;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractMenuController {
    Logger logger = LoggerFactory.getLogger(VoteController.class);
    @Autowired
    private MenuService menuService;
    
    protected List<Menu> getByDate(@RequestParam LocalDate date) {
        logger.info("Get by date = " + date);
        return menuService.getByDate(date);
    }
    
    protected Menu save(@PathVariable Integer restaurantId, @RequestBody Menu menu) {
        logger.info(String.format("Save menu =%s for restaurant id = %s", restaurantId, menu));
        return menuService.save(menu, restaurantId);
    }
    
}
