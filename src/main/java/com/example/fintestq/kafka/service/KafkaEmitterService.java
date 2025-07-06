package com.example.fintestq.kafka.service;

import com.example.fintestq.kafka.model.StockData;
import com.example.fintestq.kafka.model.StockFinancials;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KafkaEmitterService {

  @Inject
  @Channel("stock-trade-data")
  Emitter<StockData> tradeDataEmitter;

  @Inject
  @Channel("stock-financial-data")
  Emitter<StockFinancials> priceUpdateEmitter;

  public void sendStockData(StockData request) {
    tradeDataEmitter.send(request);
  }

  public void sendFinancialData(StockFinancials update) {
    priceUpdateEmitter.send(update);
  }
}

