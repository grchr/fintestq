package com.example.fintestq.service;

import com.example.fintestq.model.YahooFullStockData;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;
import org.opensource.model.CompanyBalanceSheet;
import org.opensource.model.CompanyCashFlow;
import org.opensource.model.CompanyIncomeStatement;
import org.opensource.model.CompanyKeyStatistics;
import org.opensource.service.GetBalanceSheetService;
import org.opensource.service.GetCashFlowService;
import org.opensource.service.GetIncomeStatementService;
import org.opensource.service.GetKeyStatisticsService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class GetYahooStockDataServiceImpl implements IGetYahooStockDataService {

  private static final Logger LOGGER = Logger.getLogger(GetYahooStockDataServiceImpl.class);

  public YahooFullStockData getStock(String symbol) throws IOException {
    LOGGER.info("Applying main data fetching service");
    GetKeyStatisticsService getKeyStatisticsService = new GetKeyStatisticsService();
    CompanyKeyStatistics companyKeyStatistics = getKeyStatisticsService.execute(symbol);

    GetBalanceSheetService getBalanceSheetService = new GetBalanceSheetService();
    CompanyBalanceSheet companyBalanceSheet = getBalanceSheetService.execute(symbol);

    GetIncomeStatementService getIncomeStatementService = new GetIncomeStatementService();
    CompanyIncomeStatement companyIncomeStatement = getIncomeStatementService.execute(symbol);

    GetCashFlowService getCashFlowService = new GetCashFlowService();
    CompanyCashFlow companyCashFlow = getCashFlowService.execute(symbol);

    return new YahooFullStockData(companyKeyStatistics, companyBalanceSheet, companyCashFlow, companyIncomeStatement);
  }

  public YahooFullStockData getStockAsync(String symbol) throws ExecutionException, InterruptedException {
    LOGGER.info("Applying main data fetching service");
    GetKeyStatisticsService getKeyStatisticsService = new GetKeyStatisticsService();
    CompletableFuture<CompanyKeyStatistics> companyKeyStatistics = getKeyStatisticsService.executeAsync(symbol);

    GetBalanceSheetService getBalanceSheetService = new GetBalanceSheetService();
    CompletableFuture<CompanyBalanceSheet> companyBalanceSheet = getBalanceSheetService.executeAsync(symbol);

    GetIncomeStatementService getIncomeStatementService = new GetIncomeStatementService();
    CompletableFuture<CompanyIncomeStatement> companyIncomeStatement = getIncomeStatementService.executeAsync(symbol);

    GetCashFlowService getCashFlowService = new GetCashFlowService();
    CompletableFuture<CompanyCashFlow> companyCashFlow = getCashFlowService.executeAsync(symbol);

    return new YahooFullStockData(companyKeyStatistics.get(), companyBalanceSheet.get(), companyCashFlow.get(), companyIncomeStatement.get());
  }
}
