package com.bajajbroking.bajaj_trading_sdk.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    private String userId;     // owner
    private String symbol;

    @Enumerated(EnumType.STRING)
    private OrderSide side;

    @Enumerated(EnumType.STRING)
    private OrderStyle style;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int quantity;
    private int filledQuantity;
    private Double price;      // null for MARKET
    private Instant createdAt;

    // ✅ Required by JPA
    public Order() {}

    // ✅ Used by OrderService
    public Order(String userId,
                 String symbol,
                 OrderSide side,
                 OrderStyle style,
                 int quantity,
                 Double price) {

        this.orderId = UUID.randomUUID().toString();
        this.userId = userId;
        this.symbol = symbol;
        this.side = side;
        this.style = style;
        this.status = OrderStatus.NEW;
        this.quantity = quantity;
        this.filledQuantity = 0;
        this.price = price;
        this.createdAt = Instant.now();
    }

    // ===== Getters & Setters =====

    public String getOrderId() { return orderId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public OrderSide getSide() { return side; }
    public void setSide(OrderSide side) { this.side = side; }

    public OrderStyle getStyle() { return style; }
    public void setStyle(OrderStyle style) { this.style = style; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getFilledQuantity() { return filledQuantity; }
    public void setFilledQuantity(int filledQuantity) {
        this.filledQuantity = filledQuantity;
    }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Instant getCreatedAt() { return createdAt; }
}
