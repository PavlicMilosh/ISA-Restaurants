package com.isa.restaurant.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Q on 10-Jun-17.
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class DrinkOrderDTO
{
    private Long id;
    private String name;
    private String description;
    private Double pricePerOne;
    private Integer quantity;
}
