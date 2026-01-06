package com.bajajbroking.bajaj_trading_sdk.controller;

import com.bajajbroking.bajaj_trading_sdk.service.InstrumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/instruments")
public class InstrumentController {
    private final InstrumentService service;
    public InstrumentController(InstrumentService service) { this.service = service; }

    @GetMapping
    public Object all() { return service.all(); }
}
