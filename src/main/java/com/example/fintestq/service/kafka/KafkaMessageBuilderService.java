package com.example.fintestq.service.kafka;

import com.example.fintestq.model.kafka.StockData;
import com.example.fintestq.model.kafka.StockFinancials;
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
            .totalDebt(rawData.getCompanyBalanceSheet().getTotalDebt())
            .freeCashFlowTTM(rawData.getCompanyCashFlow().getFreeCashFlowTTM())
            .build();
  }
}
