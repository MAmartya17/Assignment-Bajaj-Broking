package com.bajajbroking.bajaj_trading_sdk.service;

import com.bajajbroking.bajaj_trading_sdk.dto.OrderRequest;
import com.bajajbroking.bajaj_trading_sdk.model.*;
import com.bajajbroking.bajaj_trading_sdk.repository.OrderRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final InstrumentService instrumentService;
    private final TradeService tradeService;
    private final PortfolioService portfolioService;
    private final FundsService fundsService;

    // In-memory order book per symbol (LIMIT orders)
    private final Map<String, OrderBook> orderBooks = new ConcurrentHashMap<>();

    public OrderService(OrderRepository orderRepo,
                        InstrumentService instrumentService,
                        TradeService tradeService,
                        PortfolioService portfolioService,
                        FundsService fundsService) {

        this.orderRepo = orderRepo;
        this.instrumentService = instrumentService;
        this.tradeService = tradeService;
        this.portfolioService = portfolioService;
        this.fundsService = fundsService;
    }

    // =========================
    // PLACE ORDER
    // =========================
    @Transactional
    public Order placeOrder(String userId, OrderRequest req) {

        // basic validations
        if (req.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }

        if (req.getStyle() == OrderStyle.LIMIT && req.getPrice() == null) {
            throw new IllegalArgumentException("Price is mandatory for LIMIT orders");
        }

        Instrument instrument = instrumentService.find(req.getSymbol());
        if (instrument == null) {
            throw new NoSuchElementException("Instrument not found: " + req.getSymbol());
        }

        // funds check for BUY
        if (req.getSide() == OrderSide.BUY) {
            fundsService.checkFunds(
                    userId,
                    req.getQuantity(),
                    instrument.getLastTradedPrice()
            );
        }

        Order order = new Order(
                userId,
                req.getSymbol(),
                req.getSide(),
                req.getStyle(),
                req.getQuantity(),
                req.getPrice()
        );

        order.setStatus(OrderStatus.PLACED);
        orderRepo.save(order);

        // MARKET → immediate execution
        if (req.getStyle() == OrderStyle.MARKET) {
            tradeService.executeMarketOrder(order, instrument.getLastTradedPrice());
            order.setStatus(OrderStatus.EXECUTED);
            orderRepo.save(order);
        }
        // LIMIT → add to order book
        else {
            orderBooks
                    .computeIfAbsent(req.getSymbol(), k -> new OrderBook())
                    .add(order);
        }

        return order;
    }

    // =========================
    // GET ORDER BY ID
    // =========================
    public Order getOrder(String orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() ->
                        new NoSuchElementException("Order not found: " + orderId));
    }

    // =========================
    // GET ALL ORDERS
    // =========================
    public List<Order> allOrders() {
        return orderRepo.findAll();
    }

    // =========================
    // CANCEL ORDER
    // =========================
    @Transactional
    public void cancelOrder(String userId, String orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() ->
                        new NoSuchElementException("Order not found: " + orderId));

        if (!order.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Unauthorized order cancel");
        }

        if (order.getStatus() == OrderStatus.EXECUTED) {
            throw new IllegalStateException("Cannot cancel executed order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
    }
}
