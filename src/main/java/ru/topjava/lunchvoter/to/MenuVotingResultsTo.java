package ru.topjava.lunchvoter.to;

import java.time.LocalDate;

public class MenuVotingResultsTo {
    private final Integer menuId;
    private final LocalDate date;
    private final String restaurantName;
    private final Long votesCount;
    
    public MenuVotingResultsTo(Integer menuId, LocalDate date, String restaurantName, Long votesCount) {
        this.menuId = menuId;
        this.date = date;
        this.restaurantName = restaurantName;
        this.votesCount = votesCount;
    }
}
