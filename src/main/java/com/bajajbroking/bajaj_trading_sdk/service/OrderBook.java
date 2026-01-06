package com.bajajbroking.bajaj_trading_sdk.service;

import com.bajajbroking.bajaj_trading_sdk.model.Order;
import com.bajajbroking.bajaj_trading_sdk.model.OrderSide;

import java.util.PriorityQueue;

public class OrderBook {

    // BUY: highest price first
    private final PriorityQueue<Order> buyOrders =
            new PriorityQueue<>((a, b) -> Double.compare(b.getPrice(), a.getPrice()));

    // SELL: lowest price first
    private final PriorityQueue<Order> sellOrders =
            new PriorityQueue<>((a, b) -> Double.compare(a.getPrice(), b.getPrice()));

    public void add(Order order) {
        if (order.getSide() == OrderSide.BUY) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
    }
}
