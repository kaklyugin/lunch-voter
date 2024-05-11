package ru.topjava.lunchvoter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "menu_id", "date"}, name = "vote_unique_user_menu_date_idx")})
public class Vote extends AbstractBaseEntity {
    //TODO Override ID for UUID since there can be very many records
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
    
    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;
    
    public Vote() {
    }
    
    public Vote(User user, Menu menu, LocalDate date) {
        this.user = user;
        this.menu = menu;
        this.date = date;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
