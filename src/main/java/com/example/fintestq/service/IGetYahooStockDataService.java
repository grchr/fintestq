package com.example.fintestq.service;

import com.example.fintestq.model.YahooFullStockData;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface IGetYahooStockDataService {
  YahooFullStockData getStock(String symbol) throws IOException;
  YahooFullStockData getStockAsync(String symbol) throws ExecutionException, InterruptedException;
}

