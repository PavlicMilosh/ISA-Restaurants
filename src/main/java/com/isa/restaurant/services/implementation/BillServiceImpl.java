package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Bill;
import com.isa.restaurant.repositories.BillRepository;
import com.isa.restaurant.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djuro on 4/21/2017.
 */
@Service
public class BillServiceImpl implements BillService
{
    @Autowired
    private BillRepository billRepository;

    public Bill addBill(Bill bill){
        Bill saved = null;
        try
        {
            saved = billRepository.save(bill);
        }
        catch(Exception e)
        {
        }
        return saved;
    }

    public Bill getBillById(long id){
        Bill bill = null;
        try
        {
            bill = billRepository.findById(id);
        }
        catch(Exception e)
        {
        }
        return bill;
    }
}
