package com.isa.restaurant.services;

import com.isa.restaurant.domain.ShoppingList;

/**
 * Created by Milos on 04-Jun-17.
 */

public interface ShoppingService
{
    Boolean addShoppinglist(ShoppingList shoppingList, Long rmId);
}
