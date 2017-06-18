package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.Bill;
import com.isa.restaurant.domain.DTO.BillDTO;
import com.isa.restaurant.domain.Order;
import com.isa.restaurant.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by djuro on 4/24/2017.
 */
@RestController
@RequestMapping(value = "/bill")
@CrossOrigin
public class BillController
{
    @Autowired
    private BillService billService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bill> addOrder(@RequestBody Bill bill){
        Bill saved = billService.addBill(bill);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{tableId}/getBill/{userId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillDTO> addOrder(@PathVariable Long tableId, @PathVariable Long userId)
    {
        BillDTO bill= billService.getBill(tableId,userId);
        return new ResponseEntity(bill, HttpStatus.OK);
    }
}
