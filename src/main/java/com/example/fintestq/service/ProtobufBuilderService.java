package com.example.fintestq.service;

import com.example.fintestq.kafka.model.StockData;
import com.example.fintestq.model.YahooFullStockData;
import com.opensource.yfmodels.CompanyTradingInformationProto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class ProtobufBuilderService {

  public CompanyTradingInformationProto.CompanyTradingInformation buildTradingInfoData(YahooFullStockData rawData) {
    return CompanyTradingInformationProto.CompanyTradingInformation.newBuilder()
            .setCompanyName(rawData.getCompanyKeyStatistics().getCompanyName())
            .setCompanyTicker(rawData.getCompanyKeyStatistics().getCompanyTicker())
            .setCurrentPrice(rawData.getCompanyKeyStatistics().getCurrentPrice())
            .setForwardAnnualDividendRate(rawData.getCompanyKeyStatistics().getTradingInformation().getForwardAnnualDividendRate())
            .build();
  }
}
