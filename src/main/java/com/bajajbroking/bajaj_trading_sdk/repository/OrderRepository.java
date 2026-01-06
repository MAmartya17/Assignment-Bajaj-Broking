package com.bajajbroking.bajaj_trading_sdk.repository;

import com.bajajbroking.bajaj_trading_sdk.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {}
