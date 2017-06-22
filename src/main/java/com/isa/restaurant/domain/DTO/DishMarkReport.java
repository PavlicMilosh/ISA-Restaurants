package com.isa.restaurant.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by Milos on 22-Jun-17.
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class DishMarkReport
{
    private Long id;
    private String name;
    private String description;
    private Double meanMark;
    private Double cookMark;
    private Integer noMarks;
    private Integer noCookMarks;
}
