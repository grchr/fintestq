package com.example.fintestq.service;

import com.example.fintestq.model.YahooFullStockData;
import com.example.fintestq.model.kafka.StockData;
import com.example.fintestq.model.kafka.StockFinancials;
import com.example.fintestq.service.kafka.KafkaEmitterService;
import com.example.fintestq.service.kafka.KafkaMessageBuilderService;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//@Priority(10)
//@Decorator
public class GetYahooStockDataKafkaEmitService { // implements IGetYahooStockDataService{

//  private static final Logger LOGGER = Logger.getLogger(GetYahooStockDataKafkaEmitService.class);
//
//  @Inject
//  @Delegate
//  private GetYahooStockDataServiceImpl yahooFetchingService;
//  @Inject
//  private KafkaEmitterService kafkaEmitterService;
//  @Inject
//  private KafkaMessageBuilderService kafkaMessageBuilderService;
//
//
//  @Override
//  public YahooFullStockData getStock(String symbol) throws IOException {
//    LOGGER.info("Applying Kafka decorated data fetching service");
//    YahooFullStockData stock = yahooFetchingService.getStock(symbol);
//    sendKafkaMessages(symbol, stock);
//    return stock;
//  }
//
//  @Override
//  public YahooFullStockData getStockAsync(String symbol) throws ExecutionException, InterruptedException {
//    LOGGER.info("Applying Kafka decorated data fetching service");
//    YahooFullStockData stock = yahooFetchingService.getStockAsync(symbol);
//    sendKafkaMessages(symbol, stock);
//    return stock;
//  }
//
//  private void sendKafkaMessages(String symbol, YahooFullStockData stock) {
//    CompletableFuture.runAsync(() -> {
//      try {
//        StockData stockData = kafkaMessageBuilderService.buildStockData(stock);
//        kafkaEmitterService.sendStockData(stockData);
//        StockFinancials stockFinancials = kafkaMessageBuilderService.buildStockFinancials(stock);
//        kafkaEmitterService.sendFinancialData(stockFinancials);
//        LOGGER.infof("Kafka message sent for %s", symbol);
//      } catch (Exception e) {
//        LOGGER.warnf("Kafka message failed for %s: %s", symbol, e.getMessage());
//      }
//    });
//  }
}
