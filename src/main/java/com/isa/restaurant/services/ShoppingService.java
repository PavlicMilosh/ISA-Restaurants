package com.isa.restaurant.services;

import com.isa.restaurant.domain.Offer;
import com.isa.restaurant.domain.ShoppingList;

import java.util.List;

/**
 * Created by Milos on 04-Jun-17.
 */

public interface ShoppingService
{
    Boolean addShoppinglist(ShoppingList shoppingList, Long rmId);

    List<ShoppingList> getShoppingLists();

    List<ShoppingList> getShoppingLists(Long rmId);

    Offer sendOffer(Offer o, Long providerId, Long ShoppingListId);

    Boolean acceptOffer(Offer o, Long shoppingListId);
}
