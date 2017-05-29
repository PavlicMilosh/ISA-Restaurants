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

    public void addTable(RestaurantTable t)
    {
        this.tables.add(t);
    }
}
