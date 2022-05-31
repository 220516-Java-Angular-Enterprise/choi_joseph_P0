package com.revature.mcd.services;

import com.revature.mcd.daos.OrderDAO;
import com.revature.mcd.models.Order;
import com.revature.mcd.util.annotations.Inject;

public class OrderService {
    @Inject
    private final OrderDAO orderDAO;
    public OrderService(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    public void add(Order order) {
        orderDAO.save(order);
    }
}
