package ru.topjava.lunchvoter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedBaseEntity {
    @OneToMany(mappedBy = "restaurant")
    private Set<Dish> dishes;
    
    public Restaurant(Integer id, String name) {
        super(id, name);
    }
    
    public Restaurant() {
        super();
    }
    
}
