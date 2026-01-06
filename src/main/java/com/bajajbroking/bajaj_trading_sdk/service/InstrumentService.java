package com.bajajbroking.bajaj_trading_sdk.service;


import com.bajajbroking.bajaj_trading_sdk.model.Instrument;
import com.bajajbroking.bajaj_trading_sdk.repository.InstrumentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentService {
    private final InstrumentRepository repo;

    public InstrumentService(InstrumentRepository repo) { this.repo = repo; }

    @PostConstruct
    public void init() {
        if (repo.count() == 0) {
            repo.save(new Instrument("RELIANCE", "NSE", "EQ", 2400.50));
            repo.save(new Instrument("TCS", "NSE", "EQ", 3200.75));
            repo.save(new Instrument("INFY", "NSE", "EQ", 1500.25));
        }
    }

    public List<Instrument> all() { return repo.findAll(); }
    public Instrument find(String symbol) { return repo.findById(symbol).orElse(null); }
    public void updateLtp(String symbol, double ltp) {
        Instrument i = repo.findById(symbol).orElse(null);
        if (i != null) { i.setLastTradedPrice(ltp); repo.save(i); }
    }
}

