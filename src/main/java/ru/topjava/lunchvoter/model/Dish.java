package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "dish_name_restaurant_unique_idx")})
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
    
    public Dish(Integer id, String name, Float price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }
}
