package com.example.fintestq.service;


import jakarta.enterprise.context.ApplicationScoped;
import org.opensource.exceptions.YahooServiceException;
import org.opensource.model.response.charts.YahooEventChart;
import org.opensource.service.v2.ChartsService;

@ApplicationScoped
public class GetYahooChartDataServiceImpl {

  public YahooEventChart getChartData(String symbol) throws YahooServiceException {
    // Implementation to fetch chart data from Yahoo Finance API
    ChartsService chartsServiceAsync = new ChartsService();
    YahooEventChart chartData = chartsServiceAsync.execute(symbol);
    return chartData;
  }
}
