package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.Offer;
import com.isa.restaurant.domain.ShoppingList;
import com.isa.restaurant.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Milos on 04-Jun-17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/shopping")
public class ShoppingController
{
    @Autowired
    private ShoppingService shoppingService;

    @RequestMapping(value = "/{rmId}/addShoppingList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity addShoppingList(@PathVariable Long rmId, @RequestBody ShoppingList shoppingList)
    {
        Boolean success = shoppingService.addShoppinglist(shoppingList, rmId);
        if(success)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShoppingList>> getShoppingLists()
    {
        return new ResponseEntity<>(shoppingService.getShoppingLists(), HttpStatus.OK);
    }

    @RequestMapping(value = "/RM/{rmId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShoppingList>> getShoppingListsByRMId(@PathVariable Long rmId)
    {
        return new ResponseEntity<>(shoppingService.getShoppingLists(rmId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{providerId}/{shoppingListId}/sendOffer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> sendOffer(@RequestBody Offer o, @PathVariable Long providerId, @PathVariable Long shoppingListId)
    {
        Offer sentOffer;
        try
        {
            sentOffer = shoppingService.sendOffer(o, providerId, shoppingListId);
        }
        catch (OptimisticLockingFailureException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(sentOffer != null) return new ResponseEntity<>(sentOffer, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "{listId}/acceptOffer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity acceptOffer(@RequestBody Offer o, @PathVariable Long listId)
    {
        Boolean success;
        try
        {
            success = shoppingService.acceptOffer(o, listId);
        }
        catch (OptimisticLockingFailureException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(success) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

}
