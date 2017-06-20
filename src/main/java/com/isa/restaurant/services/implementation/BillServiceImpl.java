package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Bill;
import com.isa.restaurant.domain.DTO.BillDTO;
import com.isa.restaurant.domain.DTO.OrderDTO;
import com.isa.restaurant.domain.DTO.OrderItemDTO;
import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.RestaurantTable;
import com.isa.restaurant.domain.User;
import com.isa.restaurant.repositories.BillRepository;
import com.isa.restaurant.repositories.OrderRepository;
import com.isa.restaurant.repositories.TableRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 4/21/2017.
 */
@Service
public class BillServiceImpl implements BillService
{
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderRepository orderRepository;

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

    public BillDTO getBill(Long tableId, Long waiterId)
    {
        Set<Order> orders=orderRepository.getTableOrders(tableId);
        Set<Order> billOrders=new HashSet<Order>();
        Set<OrderItemDTO> ordersDTO = new HashSet<OrderItemDTO>();
        for (Order o:orders) {
            if(o.getDelivered()==true && o.getBillCreated()==false) {
                ordersDTO.add(new OrderItemDTO(o));
                billOrders.add(o);
                o.setBillCreated(true);
                orderRepository.save(o);
            }
        }
        User u = userRepository.findOne(waiterId);
        RestaurantTable table=tableRepository.getOne(tableId);
        Bill bill = new Bill(u, table, billOrders);
        bill.calculateBillPrice();
        billRepository.save(bill);
        BillDTO billDTO = new BillDTO(bill.getId(),ordersDTO,bill.getPrice());
        return billDTO;
    }
}
