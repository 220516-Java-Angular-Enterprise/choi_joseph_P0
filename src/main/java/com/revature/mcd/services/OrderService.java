package com.revature.mcd.services;

import com.revature.mcd.daos.OrderDAO;
import com.revature.mcd.models.Order;
import com.revature.mcd.util.annotations.Inject;

import java.util.List;

public class OrderService {
    @Inject
    private final OrderDAO orderDAO;
    public OrderService(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    public void add(Order order) {
        orderDAO.save(order);
    }

    public List<Order> getOrdersByUserID(String id){
        return orderDAO.getOrdersByUserID(id);
    }

    public Order getById(String id){
        return orderDAO.getById(id);
    }

    public Order getOrderByID(String id) {
        return orderDAO.getById(id);
    }
}
