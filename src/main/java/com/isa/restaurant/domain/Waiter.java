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
 * Created by djuro on 4/13/2017.
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "waiter")
public class Waiter extends User
{
    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkSchedule> schedule;
    //private Set<RestaurantTable> tables;

    @ManyToOne
    @JoinColumn(referencedColumnName = "dish_type_id", name = "barman_dish_type_id")
    private DishType dishType;

    public Waiter(String email, String password, String firstName, String lastName, DishType dishType)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dishType = dishType;
        //this.tables=new HashSet<RestaurantTable>();
    }

    public void addRestaurantTable(RestaurantTable table)
    {
        //tables.add(table);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SystemManager)) return false;

        User user = (SystemManager) o;

        if (id != null ? !id.equals(user.id) : false) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;

    }

    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

}
