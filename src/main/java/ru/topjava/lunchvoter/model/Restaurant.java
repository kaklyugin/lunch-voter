package ru.topjava.lunchvoter.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "restaurant")
@JsonIdentityInfo(
        scope = Restaurant.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Restaurant extends AbstractNamedBaseEntity {
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private Set<Dish> dishes;
    
    public Restaurant(Long id, String name) {
        super(id, name);
    }
    
    public Restaurant() {
        super();
    }
    
    @Override
    public String toString() {
        return "Restaurant{" +
                super.toString() +
                '}';
    }
}
