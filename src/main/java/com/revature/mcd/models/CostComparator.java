package com.revature.mcd.models;

import java.util.Comparator;

public class CostComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        return a.getOrderCost().compareTo(b.getOrderCost());
    }
}
