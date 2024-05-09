package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lunch_menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "restaurant_id"}, name = "lunch_menu_unique_date_restaurant_idx")})
public class LunchMenu extends AbstractBaseEntity {
    @Column(name = "date", nullable = false)
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "lunch_dish",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "dish_id"},name = "lunch_dish_unique_menu_dish_idx")},
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private Set<Dish> dishes = new HashSet<>();
    
    public void addDish(Dish dish)
    {
        this.dishes.add(dish);
    }
    
    public LunchMenu() {
        super();
    }
    
    public LunchMenu(Integer id, Restaurant restaurant, LocalDate date) {
        this.id = id;
        this.restaurant = restaurant;
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "LunchMenu{" +
                "id=" + id +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                '}';
    }
}
