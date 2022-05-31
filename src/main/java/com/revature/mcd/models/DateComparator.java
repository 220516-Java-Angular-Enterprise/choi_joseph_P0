package com.revature.mcd.models;

import java.util.Comparator;

public class DateComparator implements Comparator<Order> {

    @Override
    public int compare(Order a, Order b) {
        return a.getOrderDate().compareTo(b.getOrderDate());
    }
}