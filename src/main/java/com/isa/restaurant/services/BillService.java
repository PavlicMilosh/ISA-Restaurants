package com.isa.restaurant.services;

import com.isa.restaurant.domain.Bill;
import com.isa.restaurant.domain.DTO.BillDTO;

/**
 * Created by djuro on 4/21/2017.
 */
public interface BillService
{
    public Bill addBill(Bill bill);

    public Bill getBillById(long id);

    public BillDTO getBill(Long tableId, Long waiterId);
}
