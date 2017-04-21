package com.isa.restaurant.services;

import com.isa.restaurant.domain.Bill;

/**
 * Created by djuro on 4/21/2017.
 */
public interface BillService
{
    public Bill addBill(Bill bill);

    public Bill getBillById(long id);
}
