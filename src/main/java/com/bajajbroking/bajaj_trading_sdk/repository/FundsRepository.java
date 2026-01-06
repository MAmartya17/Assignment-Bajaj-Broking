package com.bajajbroking.bajaj_trading_sdk.repository;
import com.bajajbroking.bajaj_trading_sdk.model.Funds;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FundsRepository extends JpaRepository<Funds, String> {}