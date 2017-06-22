package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by djuro on 6/20/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Entity
@Table(name = "drink_mark",
        uniqueConstraints = @UniqueConstraint(columnNames = {"drink_mark_guest_id", "drink_mark_drink_id"}))
public class DrinkMark
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "drink_mark_id")
    private Long id;

    @Column(name = "drink_mark_value")
    private Double value;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "drink_mark_guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(referencedColumnName = "drink_id", name = "drink_mark_drink_id")
    private Drink drink;

    public DrinkMark(double value, Guest guest, Drink drink)
    {
        this.value=value;
        this.guest=guest;
        this.drink=drink;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof DrinkMark )) return false;

        DrinkMark  drinkMark = (DrinkMark ) o;

        if (id != null ? !id.equals(drinkMark.id) : false) return false;
        if (value != null ? !value.equals(drinkMark.value) :drinkMark.value != null) return false;
        if (guest != null ? !guest.equals(drinkMark.guest) : drinkMark.guest != null) return false;
        if (drink != null ? !drink.equals(drinkMark.drink) : drinkMark.drink != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (guest != null ? guest.hashCode() : 0);
        result = 31 * result + (drink != null ? drink.hashCode() : 0);
        return result;
    }


}
