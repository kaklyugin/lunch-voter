package ru.topjava.lunchvoter.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "restaurant_id"}, name = "menu_unique_date_restaurant_idx")})
@JsonIdentityInfo(
        scope = Menu.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Menu extends AbstractBaseEntity {
    @Column(name = "date", nullable = false)
    private LocalDate date;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    //@JsonBackReference (value = "menu-restaurant")
    private Restaurant restaurant;
    
    @ManyToMany
    @JoinTable(
            name = "menu_dish",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "dish_id"}, name = "menu_dish_unique_idx")},
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private Set<Dish> dishes = new HashSet<>();
    
    public void addDish(Dish dish) {
        this.dishes.add(dish);
    }
    
    public void clearDishes() {
        this.dishes.clear();
    }
    
    public Set<Dish> getDishes() {
        return dishes;
    }
    
    public Menu() {
        super();
    }
    
    public Menu(Integer id, Restaurant restaurant, LocalDate date) {
        this.id = id;
        this.restaurant = restaurant;
        this.date = date;
    }
    
    //TODO check if needed
    public LocalDate getDate() {
        return date;
    }
    
    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                '}';
    }
}
