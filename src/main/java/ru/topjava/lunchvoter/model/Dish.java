package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedBaseEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    private Float price;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    
    @ManyToMany(mappedBy = "dishes")
    public Set<LunchMenu> lunchMenu;
    
    public Dish() {
        super();
    }
}
