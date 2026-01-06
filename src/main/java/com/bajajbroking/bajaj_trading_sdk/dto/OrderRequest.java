package com.bajajbroking.bajaj_trading_sdk.dto;

import com.bajajbroking.bajaj_trading_sdk.model.OrderSide;
import com.bajajbroking.bajaj_trading_sdk.model.OrderStyle;

public class OrderRequest {

    private String symbol;
    private OrderSide side;     // BUY / SELL
    private OrderStyle style;   // MARKET / LIMIT
    private int quantity;
    private Double price;       // null for MARKET

    // ✅ REQUIRED: no-arg constructor
    public OrderRequest() {}

    // ===== Getters & Setters =====

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public OrderStyle getStyle() {
        return style;
    }

    public void setStyle(OrderStyle style) {
        this.style = style;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
