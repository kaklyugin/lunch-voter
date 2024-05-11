package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "user_unique_email_idx")})
public class User extends AbstractNamedBaseEntity {
    @Column(name = "email", nullable = false)
    @NotBlank
    @Email
    @Size(max = 128)
    private String email;
    
    @Enumerated(EnumType.STRING)
    //TODO Провести эксперимент - выключить FetchType.EAGER
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role")})
    @Column(name = "role")
    private Set<Role> roles;
    
    public User() {
        super();
    }
    
    public User(Integer id, String name, String email, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.roles = roles;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", roles=" + roles +
                ", id=" + id +
                '}';
    }
}
