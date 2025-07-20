package com.example.fintestq.service;

import com.example.fintestq.exceptions.BusinessException;
import com.example.fintestq.model.MetricsData;
import com.example.fintestq.model.YahooFullStockData;
import com.example.fintestq.utils.BigDecimalHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@ApplicationScoped
public class ComputationalService {

  private static final Logger LOGGER = Logger.getLogger(ComputationalService.class);

  private static final int SCALE = 10;

  private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

  @Inject
  private GetYahooStockDataServiceImpl getYahooDataService;

  public MetricsData getFullComputationData(String symbol) throws BusinessException {
    try {
      YahooFullStockData stock = this.getYahooDataService.getStock(symbol);
      BigDecimal marketCap = BigDecimalHelper.parseDouble(calculateMarketCap(stock));
      BigDecimal freeCashFlowTTM = BigDecimalHelper.parseDouble(stock.getCompanyCashFlow().getFreeCashFlowTTM());
      BigDecimal freeCashFlowYield = freeCashFlowTTM.divide(marketCap, SCALE, ROUNDING_MODE);
      BigDecimal netDebt = BigDecimalHelper.parseDouble(stock.getCompanyBalanceSheet().getNetDebt());
      BigDecimal ebitda = BigDecimalHelper.parseDoubleWithEndChar(stock.getCompanyKeyStatistics().getEBITDA());
      BigDecimal netDebtToEBITDA = netDebt.divide(ebitda, SCALE, ROUNDING_MODE);

      MetricsData.Builder metricsDataBuilder = new MetricsData.Builder()
              .withFreeCashFlowMargin(freeCashFlowYield)
              .withNetDebtToEbitdaRatio(netDebtToEBITDA);

      return metricsDataBuilder.build();
    } catch (IOException | ArithmeticException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  private static double calculateMarketCap(YahooFullStockData stock) {
    double ordinarySharesNumber = stock.getCompanyBalanceSheet().getOrdinarySharesNumber();
    double currentPrice = stock.getCompanyKeyStatistics().getCurrentPrice();
    return ordinarySharesNumber * currentPrice;
  }
}
