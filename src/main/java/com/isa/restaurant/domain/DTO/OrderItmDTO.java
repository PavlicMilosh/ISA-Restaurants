package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 6/22/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItmDTO
{
    private Long id;
    private DishOrderDTO dish;
    private DrinkOrderDTO drink;
    private Boolean isDish;
    private Integer number;
    private Boolean preparing;
    private Boolean finished;
    private Long userId;

    public OrderItmDTO(OrderItem orderItem)
    {
        this.id=orderItem.getId();
        this.isDish=orderItem.getIsDish();
        if(isDish) {
            this.dish=new DishOrderDTO(orderItem.getDish());
            this.drink=null;
        }
        else{
            this.dish=null;
            this.drink=new DrinkOrderDTO(orderItem.getDrink());
        }
        this.number=orderItem.getNumber();
        this.preparing=orderItem.getPreparing();
        this.finished=orderItem.getFinished();
        this.userId=orderItem.getUserId();
    }
}
