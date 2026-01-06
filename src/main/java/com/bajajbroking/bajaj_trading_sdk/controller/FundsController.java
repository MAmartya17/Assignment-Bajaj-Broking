package com.bajajbroking.bajaj_trading_sdk.controller;

import com.bajajbroking.bajaj_trading_sdk.model.Funds;
import com.bajajbroking.bajaj_trading_sdk.service.FundsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/funds")
public class FundsController {
    private final FundsService service;
    public FundsController(FundsService service) { this.service = service; }

    @GetMapping
    public Funds get(HttpServletRequest r) {
        String userId = (String) r.getAttribute("userId");
        return service.getFunds(userId);
    }
}
