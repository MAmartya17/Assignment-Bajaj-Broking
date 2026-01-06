package com.bajajbroking.bajaj_trading_sdk.repository;
import com.bajajbroking.bajaj_trading_sdk.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstrumentRepository extends JpaRepository<Instrument, String> {}