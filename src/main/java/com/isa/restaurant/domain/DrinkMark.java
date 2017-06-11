package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Q on 06-Jun-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Entity
@Table(name = "drink_mark", uniqueConstraints = @UniqueConstraint(columnNames = {"drink_mark_guest_id", "drink_mark_drink_id"}))
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
}
