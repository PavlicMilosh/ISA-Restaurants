package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.BillDTO;
import com.isa.restaurant.domain.DTO.OrderDTO;
import com.isa.restaurant.domain.DTO.OrderItemDTO;
import com.isa.restaurant.repositories.*;
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

    @Autowired
    private ReservationRepository reservationRepository;

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
                if(o.getReservationId()!=null) {
                    Reservation reservation = reservationRepository.findById(o.getReservationId());
                    Boolean flag = true;
                    for(Order oo:reservation.getOrders())
                    {
                        if(!oo.getBillCreated())
                        {
                            flag=false;
                            break;
                        }
                    }
                    if(flag) reservation.setStatus(ReservationStatus.FINISHED);
                    reservationRepository.save(reservation);
                }
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
