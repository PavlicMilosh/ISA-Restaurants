package com.isa.restaurant.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by djuro on 6/18/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillDTO
{
    private Long id;
    private Set<OrderItemDTO> orders;
    private double price;
}
