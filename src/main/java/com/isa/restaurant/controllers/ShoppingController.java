package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.ShoppingList;
import com.isa.restaurant.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity addShoppingList(@PathVariable Long rmId, ShoppingList shoppingList)
    {
        Boolean success = shoppingService.addShoppinglist(shoppingList, rmId);
        if(success)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
