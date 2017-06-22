package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.DrinkDishDTO;
import com.isa.restaurant.domain.DTO.FinishedOrderDTO;
import com.isa.restaurant.domain.DTO.HistoryDTO;
import com.isa.restaurant.domain.DTO.OrderItemDTO;
import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.OrderItem;
import com.isa.restaurant.domain.User;

import java.util.Set;

/**
 * Created by djuro on 4/22/2017.
 */
public interface OrdersService
{
    public Order getById(Long id);

    public Order getByUser(User user);

    OrderItemDTO addOrder(OrderItemDTO orderDTO, Long id, Long waiterId, Long tableId);

    public Order finishOrder(Long id);

    public Set<OrderItemDTO> getOrdersForDeliver(Long waiterId);

    public Set<Long> getTablesForCreatingBill(Long waiter);

    public OrderItemDTO deliveredOrder(Long orderId, Long userId);

    public Set<Long> getPreparingOrderId(Long userId);

    public Set<FinishedOrderDTO> getGuestOrders(Long guestId);

    public HistoryDTO makeMark(HistoryDTO historyDTO, Long guestId);

    public Set<OrderItemDTO> getOrdersForChanging(Long waiterId);

    public OrderItemDTO getOrderForChanging(Long waiterId, Long tableId);

    public Boolean changeOrder(Long waiterId, OrderItemDTO orderDTO);
}
