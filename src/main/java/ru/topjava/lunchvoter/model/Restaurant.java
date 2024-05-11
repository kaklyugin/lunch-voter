package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedBaseEntity {
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Dish> dishes;
    
    public Restaurant(Integer id, String name) {
        super(id, name);
    }
    
    public Restaurant() {
        super();
    }
    
    @Override
    public String toString() {
        return "Restaurant{" +
                ", id=" + id +
                '}';
    }
}
