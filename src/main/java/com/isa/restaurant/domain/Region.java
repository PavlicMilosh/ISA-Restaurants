package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Milos on 16-May-17.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class Region
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "region_name")
    private String name;

    @Column(name = "region_color")
    private String color;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<RestaurantTable> tables;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;


    public Region()
    {
        this.tables = new HashSet<>();
    }


    public Region(String name, Restaurant r, Set<RestaurantTable> tables)
    {
        this.name = name;
        this.restaurant = r;
        this.tables = tables;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;

        Region region = (Region) o;

        if (id != null ? !id.equals(region.id) : false) return false;
        if (name != null ? !name.equals(region.name) : region.name != null) return false;
        if (color != null ? !color.equals(region.color) : region.color != null) return false;
        if (tables != null ? !tables.equals(region.tables) : region.tables != null) return false;
        if (restaurant != null ? !restaurant.equals(region.restaurant) : region.restaurant != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (tables != null ? tables.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }


    public void addTable(RestaurantTable t)
    {
        this.tables.add(t);
    }
}
