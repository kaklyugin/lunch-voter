package ru.topjava.lunchvoter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"name","price","restaurant_id"}, name = "dish_unique_name_price_restaurant_idx")})
public class Dish extends AbstractNamedBaseEntity {
    @Column(name = "price", nullable = false)
    @NotNull
    private Float price;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    //@JsonBackReference(value = "dish-restaurant")
    private Restaurant restaurant;
    
    @ManyToMany(mappedBy = "dishes")
    //@JsonBackReference
    @JsonIgnore
    public Set<Menu> menu;
    
    public Dish() {
        super();
    }
    
    public Dish(Integer id, String name, Float price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }
}
