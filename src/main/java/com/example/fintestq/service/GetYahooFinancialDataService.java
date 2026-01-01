package com.example.fintestq.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.opensource.exceptions.YahooServiceException;
import org.opensource.model.response.financials.YahooFinancials;
import org.opensource.service.v2.FinancialsService;

@ApplicationScoped
public class GetYahooFinancialDataService {

  public void getEarningData(String symbol) throws YahooServiceException {
    // Implementation to fetch earning data from Yahoo Finance API
    FinancialsService financialsService = new FinancialsService();
    YahooFinancials result = financialsService.execute(symbol);
    //result.getQuoteSummary().getResult().get(0).
  }
}
