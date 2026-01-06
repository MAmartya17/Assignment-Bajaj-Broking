package com.bajajbroking.bajaj_trading_sdk.controller;

import com.bajajbroking.bajaj_trading_sdk.model.PortfolioHolding;
import com.bajajbroking.bajaj_trading_sdk.service.PortfolioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolio")
public class PortfolioController {
    private final PortfolioService portfolioService;
    public PortfolioController(PortfolioService portfolioService) { this.portfolioService = portfolioService; }

    @GetMapping
    public List<PortfolioHolding> holdings(HttpServletRequest r) {
        String userId = (String) r.getAttribute("userId");
        return portfolioService.getHoldings(userId);
    }
}

