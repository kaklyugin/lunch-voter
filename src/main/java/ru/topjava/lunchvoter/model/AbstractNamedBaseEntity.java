package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractNamedBaseEntity extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    @NotNull
    private String name;
    
    protected AbstractNamedBaseEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
    
    protected AbstractNamedBaseEntity() {
        super();
    }
    
    @Override
    public String toString() {
        return
                "id=" + id +
                        ", name='" + name + '\'';
    }
}
