package com.bajajbroking.bajaj_trading_sdk.service;

import com.bajajbroking.bajaj_trading_sdk.model.OrderSide;
import com.bajajbroking.bajaj_trading_sdk.model.PortfolioHolding;
import com.bajajbroking.bajaj_trading_sdk.model.Trade;
import com.bajajbroking.bajaj_trading_sdk.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository repo;

    public PortfolioService(PortfolioRepository repo) {
        this.repo = repo;
    }

    // ✅ USED BY TradeService
    public synchronized void applyTrade(Trade trade) {

        PortfolioHolding holding =
                repo.findByUserIdAndSymbol(trade.getUserId(), trade.getSymbol())
                        .orElse(new PortfolioHolding(
                                trade.getUserId(),
                                trade.getSymbol(),
                                0,
                                0.0
                        ));

        if (trade.getSide() == OrderSide.BUY) {
            int newQty = holding.getQuantity() + trade.getQuantity();
            double totalCost =
                    (holding.getAveragePrice() * holding.getQuantity())
                            + (trade.getPrice() * trade.getQuantity());

            holding.setQuantity(newQty);
            holding.setAveragePrice(totalCost / newQty);
        } else { // SELL
            holding.setQuantity(holding.getQuantity() - trade.getQuantity());
        }

        repo.save(holding);
    }

    // ✅ USED BY PortfolioController
    public List<PortfolioHolding> getHoldings(String userId) {
        return repo.findByUserId(userId);
    }
}
