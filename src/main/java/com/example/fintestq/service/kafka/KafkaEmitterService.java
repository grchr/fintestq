package com.example.fintestq.service.kafka;

import com.example.fintestq.model.kafka.StockData;
import com.example.fintestq.model.kafka.StockFinancials;
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

