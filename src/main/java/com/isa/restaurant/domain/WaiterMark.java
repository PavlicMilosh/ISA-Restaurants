package com.isa.restaurant.domain;

/**
 * Created by Milos on 20-Jun-17.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Entity
@Table(name = "waiter_mark",
        uniqueConstraints = @UniqueConstraint(columnNames = {"waiter_mark_guest_id", "waiter_mark_waiter_id"}))
public class WaiterMark
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "waiter_mark_id")
    private Long id;

    @Column(name = "waiter_mark_value")
    private Double value;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "waiter_mark_guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "waiter_mark_waiter_id")
    private Waiter waiter;

    public WaiterMark(double value, Guest guest, Waiter waiter)
    {
        this.value=value;
        this.guest=guest;
        this.waiter=waiter;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof WaiterMark)) return false;

        WaiterMark waiterMark = (WaiterMark) o;

        if (id != null ? !id.equals(waiterMark.id) : false) return false;
        if (value != null ? !value.equals(waiterMark.value) : waiterMark.value != null) return false;
        if (guest != null ? !guest.equals(waiterMark.guest) : waiterMark.guest != null) return false;
        if (waiter != null ? !waiter.equals(waiterMark.waiter) : waiterMark.waiter != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (guest != null ? guest.hashCode() : 0);
        result = 31 * result + (waiter != null ? waiter.hashCode() : 0);
        return result;
    }
}
