package com.isa.restaurant.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Milos on 22-Jun-17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class CookMarkReport
{
    private Long id;
    private String firstName;
    private String lastName;
    private Double meanMark;
    private Integer noMarks;
    private ArrayList<DishMarkReport> dishes;

    public CookMarkReport()
    {
        this.dishes = new ArrayList<>();
    }
}
