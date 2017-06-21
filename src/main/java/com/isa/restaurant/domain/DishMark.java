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
@Table(name = "dish_mark",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dish_mark_guest_id", "dish_mark_dish_id"}))
public class DishMark
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "dish_mark_id")
    private Long id;

    @Column(name = "dish_mark_value")
    private Double value;

    @Column(name = "dish_mark_count_mark")
    private Integer markCount;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "dish_mark_guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(referencedColumnName = "dish_id", name = "dish_mark_dish_id")
    private Dish dish;

    public DishMark(double value, Integer markCount, Guest guest, Dish dish)
    {
        this.value=value;
        this.markCount=markCount;
        this.guest=guest;
        this.dish=dish;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof DishMark)) return false;

        DishMark dishMark = (DishMark) o;

        if (id != null ? !id.equals(dishMark.id) : false) return false;
        if (value != null ? !value.equals(dishMark.value) : dishMark.value != null) return false;
        if (markCount != null ? !markCount.equals(dishMark.markCount) : dishMark.markCount != null) return false;
        if (guest != null ? !guest.equals(dishMark.guest) : dishMark.guest != null) return false;
        if (dish != null ? !dish.equals(dishMark.dish) : dishMark.dish != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (markCount != null ? markCount.hashCode() : 0);
        result = 31 * result + (guest != null ? guest.hashCode() : 0);
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        return result;
    }

    public void mark(double mark)
    {
        this.markCount += 1;
        this.value=((this.markCount-1)/this.markCount)*this.value+mark/this.markCount;
    }
}
