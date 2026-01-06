package com.bajajbroking.bajaj_trading_sdk.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Trade {

    @Id
    @GeneratedValue
    private Long id;            // DB primary key

    private String tradeId;     // business id
    private String orderId;
    private String userId;
    private String symbol;

    @Enumerated(EnumType.STRING)
    private OrderSide side;     // BUY / SELL  ✅ MISSING EARLIER

    private int quantity;
    private double price;
    private Instant executedAt;

    // ✅ Required by JPA
    public Trade() {}

    // ✅ Used by TradeService
    public Trade(String tradeId,
                 String orderId,
                 String userId,
                 String symbol,
                 OrderSide side,
                 int quantity,
                 double price,
                 Instant executedAt) {

        this.tradeId = tradeId;
        this.orderId = orderId;
        this.userId = userId;
        this.symbol = symbol;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
        this.executedAt = executedAt;
    }

    // ===== Getters =====
    public Long getId() { return id; }
    public String getTradeId() { return tradeId; }
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public String getSymbol() { return symbol; }
    public OrderSide getSide() { return side; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public Instant getExecutedAt() { return executedAt; }
}
