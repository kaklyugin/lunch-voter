package ru.topjava.lunchvoter.to;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

public class VoteTo {
    private final Integer menuId;
    private LocalDate date;
    
    @ConstructorProperties({"id"})
    public VoteTo(Integer menuId) {
        this.menuId = menuId;
    }
    
    public Integer getMenuId() {
        return menuId;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
