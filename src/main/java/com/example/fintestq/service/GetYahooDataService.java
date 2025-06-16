package com.example.fintestq.service;

import com.example.fintestq.model.YahooFullStockData;
import jakarta.enterprise.context.ApplicationScoped;
import org.opensource.model.CompanyBalanceSheet;
import org.opensource.model.CompanyCashFlow;
import org.opensource.model.CompanyIncomeStatement;
import org.opensource.model.CompanyKeyStatistics;
import org.opensource.service.GetBalanceSheetService;
import org.opensource.service.GetCashFlowService;
import org.opensource.service.GetIncomeStatementService;
import org.opensource.service.GetKeyStatisticsService;

import java.io.IOException;

@ApplicationScoped
public class GetYahooDataService {

  public YahooFullStockData getStock(String symbol) throws IOException {
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
}
