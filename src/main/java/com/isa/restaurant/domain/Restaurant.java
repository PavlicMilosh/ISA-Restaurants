package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Milos on 11-Apr-17.
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "restaurant")
public class Restaurant
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "restaurant_name", unique = true, nullable = false)
    private String name;

    @Column(name = "restaurant_desc")
    private String description;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Dish> dishes;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Drink> drinks;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RestaurantManager> managers;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RestaurantTable> tables;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bartender> bartenders;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cook> cooks;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Waiter> waiters;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkSchedule> schedule;

    public Restaurant()
    {
        this.dishes = new HashSet<>();
        this.drinks = new HashSet<>();
        this.managers = new HashSet<>();
        this.tables = new HashSet<>();
        this.bartenders = new HashSet<>();
        this.cooks = new HashSet<>();
        this.waiters = new HashSet<>();
        this.schedule = new HashSet<>();
    }

    public Restaurant(String name, String description)
    {
        this();
        this.name = name;
        this.description = description;
    }

    public Restaurant(Restaurant other)
    {
        this.name = other.getName();
        this.description = other.getDescription();
        this.dishes = other.getDishes();
        this.drinks = other.getDrinks();
        this.managers = other.getManagers();
        this.tables = other.getTables();
        this.bartenders = other.getBartenders();
        this.cooks = other.getCooks();
        this.waiters = other.getWaiters();
        this.schedule = other.getSchedule();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;

        Restaurant that = (Restaurant) o;

        if (!name.equals(that.name)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dishes != null ? !dishes.equals(that.dishes) : that.dishes != null) return false;
        if (drinks != null ? !drinks.equals(that.drinks) : that.drinks != null) return false;
        if (managers != null ? !managers.equals(that.managers) : that.managers != null) return false;
        return tables != null ? tables.equals(that.tables) : that.tables == null;

    }

    @Override
    public int hashCode()
    {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        result = 31 * result + (drinks != null ? drinks.hashCode() : 0);
        result = 31 * result + (managers != null ? managers.hashCode() : 0);
        result = 31 * result + (tables != null ? tables.hashCode() : 0);
        return result;
    }

    public void addManager(RestaurantManager restaurantManager)
    {
        this.managers.add(restaurantManager);
    }

    public void addTable(RestaurantTable table)
    {
        this.tables.add(table);
    }

    public void addDish(Dish dish)
    {
        this.dishes.add(dish);
    }

    public void addDrink(Drink drink)
    {
        this.drinks.add(drink);
    }
}
