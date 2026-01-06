package com.bajajbroking.bajaj_trading_sdk.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Instrument {
    @Id
    private String symbol;
    private String exchange;
    private String instrumentType;
    private double lastTradedPrice;

    public Instrument() {}
    public Instrument(String symbol, String exchange, String instrumentType, double lastTradedPrice) {
        this.symbol = symbol;
        this.exchange = exchange;
        this.instrumentType = instrumentType;
        this.lastTradedPrice = lastTradedPrice;
    }
    // getters and setters
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public String getExchange() { return exchange; }
    public void setExchange(String exchange) { this.exchange = exchange; }
    public String getInstrumentType() { return instrumentType; }
    public void setInstrumentType(String instrumentType) { this.instrumentType = instrumentType; }
    public double getLastTradedPrice() { return lastTradedPrice; }
    public void setLastTradedPrice(double lastTradedPrice) { this.lastTradedPrice = lastTradedPrice; }
}
