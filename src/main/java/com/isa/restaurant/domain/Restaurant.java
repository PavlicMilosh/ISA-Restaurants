package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Milos on 11-Apr-17.
 */
@Entity
@NoArgsConstructor
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

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set<Dish> dishes;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set<Drink> drinks;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set<RestaurantManager> managers;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Set<RestaurantTable> tables;

    public Restaurant(String name, String description)
    {
        this.name = name;
        this.description = description;
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
}