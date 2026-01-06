package com.bajajbroking.bajaj_trading_sdk.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Funds {
    @Id
    private String userId;
    private double cashAvailable;
    private double buypwr;
    private double marginUtilized;

    public Funds() {}
    public Funds(String userId, double cashAvailable, double buypwr) {
        this.userId = userId;
        this.cashAvailable = cashAvailable;
        this.buypwr = buypwr;
    }
    // getters and setters
    public String getUserId() { return userId; }
    public double getCashAvailable() { return cashAvailable; }
    public void setCashAvailable(double cashAvailable) { this.cashAvailable = cashAvailable; }
    public double getBuypwr() { return buypwr; }
    public void setBuypwr(double buypwr) { this.buypwr = buypwr; }
    public double getMarginUtilized() { return marginUtilized; }
    public void setMarginUtilized(double marginUtilized) { this.marginUtilized = marginUtilized; }
}

