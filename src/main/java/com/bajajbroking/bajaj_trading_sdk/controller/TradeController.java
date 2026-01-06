package com.bajajbroking.bajaj_trading_sdk.controller;

import com.bajajbroking.bajaj_trading_sdk.model.Trade;
import com.bajajbroking.bajaj_trading_sdk.service.TradeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trades")
public class TradeController {
    private final TradeService tradeService;
    public TradeController(TradeService tradeService) { this.tradeService = tradeService; }

    @GetMapping
    public List<Trade> myTrades(HttpServletRequest r) {
        String userId = (String) r.getAttribute("userId");
        return tradeService.tradesForUser(userId);
    }
}
