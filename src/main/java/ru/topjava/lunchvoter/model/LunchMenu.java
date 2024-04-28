package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "lunch_menu")
public class LunchMenu extends AbstractBaseEntity {
    @Column(name = "date", nullable = false)
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    
    @ManyToMany
    @JoinTable(
            name = "lunch_dish",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private Set<Dish> dishes;
    
    public LunchMenu() {
    }
}
