package com.bajajbroking.bajaj_trading_sdk.controller;

import com.bajajbroking.bajaj_trading_sdk.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/edis")
public class EdisController {
    @GetMapping("/EdisRequestWeb")
    public ApiResponse edisRequest() { return new ApiResponse(0, "EdisRequestWeb stubbed", null); }

    @PostMapping("/VerifyEdis")
    public ApiResponse verify(@RequestBody Object payload) { return new ApiResponse(0, "VerifyEdis stubbed", null); }

    @GetMapping("/GenerateTPIN")
    public ApiResponse tpin() { return new ApiResponse(0, "GenerateTPIN stubbed", null); }
}
