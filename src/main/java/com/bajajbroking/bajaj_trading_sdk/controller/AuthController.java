package com.bajajbroking.bajaj_trading_sdk.controller;

import com.bajajbroking.bajaj_trading_sdk.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    // For demo: Client calls /auth/login?userId=user-1 and receives a token they can use
    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestParam(defaultValue = "user-1") String userId) {
        // In real system you'd do OAuth flow; here we return token = userId
        return ResponseEntity.ok(new ApiResponse(0, "", userId));
    }
}

