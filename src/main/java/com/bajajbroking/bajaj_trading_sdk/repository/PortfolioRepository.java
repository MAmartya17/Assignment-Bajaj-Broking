package com.bajajbroking.bajaj_trading_sdk.repository;
import com.bajajbroking.bajaj_trading_sdk.model.PortfolioHolding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<PortfolioHolding, String> {
    List<PortfolioHolding> findByUserId(String userId);
    Optional<PortfolioHolding> findByUserIdAndSymbol(String userId, String symbol);
}
