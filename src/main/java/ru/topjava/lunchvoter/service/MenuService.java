package ru.topjava.lunchvoter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.topjava.lunchvoter.model.Menu;
import ru.topjava.lunchvoter.repository.DataJpaMenuRepository;
import ru.topjava.lunchvoter.to.MenuVotingResultsTo;
import ru.topjava.lunchvoter.utils.DateUtils;
import ru.topjava.lunchvoter.utils.exception.IllegalRequestException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuService {
    Logger logger = LoggerFactory.getLogger(DataJpaMenuRepository.class);
    
    private final DataJpaMenuRepository dataJpaMenuRepository;
    
    public MenuService(DataJpaMenuRepository dataJpaMenuRepository) {
        this.dataJpaMenuRepository = dataJpaMenuRepository;
    }
    
    public List<Menu> getActual() {
        logger.info("Get actual menus");
        return getByDate(DateUtils.getActualMenuDate());
    }
    
    public List<Menu> getByDate(@RequestParam LocalDate date) {
        logger.info(String.format("Get menu by date=%s", date));
        return dataJpaMenuRepository.getByDate(date);
    }
    
    public Menu save(Menu menu, Integer restaurantId) {
        logger.info(String.format("Save menu menu=%s for restaurant id=%s", menu, restaurantId));
        return dataJpaMenuRepository.save(menu, restaurantId);
    }
    
    public void checkMenuIsActual(Integer id) {
        logger.info(String.format("Check menu is actual by menuId=%s", id));
        if (!getActualIds().contains(id)) {
            throw new IllegalRequestException(String.format("Menu with id %s is not actual", id));
        }
    }
    
    public List<MenuVotingResultsTo> getMenuVotingResults(@RequestParam LocalDate date) {
        logger.info(String.format("Get menu voting results by date=%s", date));
        return dataJpaMenuRepository.getMenuVotingResults(date);
    }
    
    private Set getActualIds() {
        logger.info("Get actual menu ids");
        return getActual().stream()
                .map(menu -> menu.getId())
                .collect(Collectors.toSet());
    }
}
