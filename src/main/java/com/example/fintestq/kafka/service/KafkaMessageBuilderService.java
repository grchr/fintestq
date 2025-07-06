package com.example.fintestq.kafka.service;

import com.example.fintestq.kafka.model.StockData;
import com.example.fintestq.kafka.model.StockFinancials;
import com.example.fintestq.model.YahooFullStockData;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaMessageBuilderService {

  public StockData buildStockData(YahooFullStockData rawData) {
    return StockData.builder()
            .symbol(rawData.getCompanyKeyStatistics().getCompanyTicker())
            .price(rawData.getCompanyKeyStatistics().getCurrentPrice())
            .build();
  }

  public StockFinancials buildStockFinancials(YahooFullStockData rawData) {
    return StockFinancials.builder()
            .symbol(rawData.getCompanyKeyStatistics().getCompanyTicker())
            .totalDebt(Double.parseDouble(rawData.getCompanyKeyStatistics().getTotalDebt()))
            .freeCashFlowTTM(rawData.getCompanyCashFlow().getFreeCashFlowTTM())
            .build();
  }
}
