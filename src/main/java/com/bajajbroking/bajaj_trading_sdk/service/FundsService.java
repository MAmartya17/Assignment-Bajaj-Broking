package com.bajajbroking.bajaj_trading_sdk.service;

import com.bajajbroking.bajaj_trading_sdk.model.Funds;
import com.bajajbroking.bajaj_trading_sdk.repository.FundsRepository;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class FundsService {

    private final FundsRepository repo;

    public FundsService(FundsRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void init() {
        if (!repo.existsById("user-1")) {
            repo.save(new Funds("user-1", 1_00_000.0, 1_00_000.0));
        }
    }

    public Funds getFunds(String userId) {
        return repo.findById(userId)
                .orElse(new Funds(userId, 0.0, 0.0));
    }

    // ✅ ADD THIS METHOD (THIS FIXES YOUR ERROR)
    public void checkFunds(String userId, int quantity, double price) {
        double requiredAmount = quantity * price;
        Funds f = getFunds(userId);

        if (f.getCashAvailable() < requiredAmount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    public synchronized void debit(String userId, double amount) {
        Funds f = repo.findById(userId)
                .orElse(new Funds(userId, 0, 0));

        if (f.getCashAvailable() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        f.setCashAvailable(f.getCashAvailable() - amount);
        repo.save(f);
    }

    public synchronized void credit(String userId, double amount) {
        Funds f = repo.findById(userId)
                .orElse(new Funds(userId, 0, 0));

        f.setCashAvailable(f.getCashAvailable() + amount);
        repo.save(f);
    }
}
