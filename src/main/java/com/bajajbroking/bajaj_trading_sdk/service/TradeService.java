package com.bajajbroking.bajaj_trading_sdk.service;

import com.bajajbroking.bajaj_trading_sdk.model.Order;
import com.bajajbroking.bajaj_trading_sdk.model.OrderSide;
import com.bajajbroking.bajaj_trading_sdk.model.Trade;
import com.bajajbroking.bajaj_trading_sdk.repository.TradeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TradeService {

    private final TradeRepository tradeRepo;
    private final PortfolioService portfolioService;
    private final FundsService fundsService;

    public TradeService(TradeRepository tradeRepo,
                        PortfolioService portfolioService,
                        FundsService fundsService) {
        this.tradeRepo = tradeRepo;
        this.portfolioService = portfolioService;
        this.fundsService = fundsService;
    }

    // ✅ USED BY OrderService
    public void executeMarketOrder(Order order, double executionPrice) {

        Trade trade = new Trade(
                UUID.randomUUID().toString(),
                order.getOrderId(),
                order.getUserId(),
                order.getSymbol(),
                order.getSide(),
                order.getQuantity(),
                executionPrice,
                Instant.now()
        );

        tradeRepo.save(trade);

        portfolioService.applyTrade(trade);

        double amount = trade.getQuantity() * executionPrice;
        if (trade.getSide() == OrderSide.BUY) {
            fundsService.debit(trade.getUserId(), amount);
        } else {
            fundsService.credit(trade.getUserId(), amount);
        }
    }

    // ✅ USED BY TradeController (THIS WAS MISSING)
    public List<Trade> tradesForUser(String userId) {
        return tradeRepo.findByUserId(userId);
    }
}
