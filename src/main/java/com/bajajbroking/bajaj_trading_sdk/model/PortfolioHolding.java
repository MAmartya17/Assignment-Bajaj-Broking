package com.bajajbroking.bajaj_trading_sdk.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PortfolioHolding {
    @Id
    private String symbolAndUser; // e.g. userId|SYMBOL
    private String userId;
    private String symbol;
    private int quantity;
    private double averagePrice;

    public PortfolioHolding() {}
    public PortfolioHolding(String userId, String symbol, int quantity, double averagePrice) {
        this.userId = userId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
        this.symbolAndUser = userId + "|" + symbol;
    }

    // getters/setters
    public String getSymbolAndUser() { return symbolAndUser; }
    public String getUserId() { return userId; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getAveragePrice() { return averagePrice; }
    public void setAveragePrice(double averagePrice) { this.averagePrice = averagePrice; }
}
