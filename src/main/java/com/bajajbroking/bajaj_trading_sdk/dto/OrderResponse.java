package com.bajajbroking.bajaj_trading_sdk.dto;


public class OrderResponse {
    private String orderId;
    private String status;
    public OrderResponse() {}
    public OrderResponse(String orderId, String status) { this.orderId = orderId; this.status = status; }
    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }
}

