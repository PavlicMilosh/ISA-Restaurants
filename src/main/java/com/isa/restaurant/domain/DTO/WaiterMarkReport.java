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
public class WaiterMarkReport
{
    private Long id;
    private String firstName;
    private String lastName;
    private Double meanMark;
}
