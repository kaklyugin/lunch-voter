package ru.topjava.lunchvoter.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.lunchvoter.service.MenuService;
import ru.topjava.lunchvoter.to.MenuVotingResultsTo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/profile/reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {
    Logger logger = LoggerFactory.getLogger(ReportController.class);
    
    private final MenuService menuService;
    
    public ReportController(MenuService menuService) {
        this.menuService = menuService;
    }
    
    @GetMapping
    public List<MenuVotingResultsTo> getActual(@RequestParam LocalDate date) {
        logger.info(String.format("Get menu voting results by date=%s", date));
        return menuService.getMenuVotingResults(date);
    }
}
