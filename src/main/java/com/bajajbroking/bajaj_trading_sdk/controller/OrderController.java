package com.bajajbroking.bajaj_trading_sdk.controller;

import com.bajajbroking.bajaj_trading_sdk.dto.OrderRequest;
import com.bajajbroking.bajaj_trading_sdk.dto.OrderResponse;
import com.bajajbroking.bajaj_trading_sdk.model.Order;
import com.bajajbroking.bajaj_trading_sdk.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse place(@Valid @RequestBody OrderRequest req, HttpServletRequest r) {
        String userId = (String) r.getAttribute("userId");
        Order o = orderService.placeOrder(userId, req);
        return new OrderResponse(o.getOrderId(), o.getStatus().name());
    }

    @GetMapping("/{orderId}")
    public Order get(@PathVariable String orderId) { return orderService.getOrder(orderId); }

    @GetMapping
    public List<Order> all() { return orderService.allOrders(); }

    @DeleteMapping("/{orderId}")
    public void cancel(@PathVariable String orderId, HttpServletRequest r) {
        String userId = (String) r.getAttribute("userId");
        orderService.cancelOrder(userId, orderId);
    }
}
