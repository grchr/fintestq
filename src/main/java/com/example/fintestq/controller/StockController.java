package com.example.fintestq.controller;

import com.example.fintestq.kafka.model.StockData;
import com.example.fintestq.kafka.model.StockFinancials;
import com.example.fintestq.kafka.service.KafkaEmitterService;
import com.example.fintestq.kafka.service.KafkaMessageBuilderService;
import com.example.fintestq.model.YahooFullStockData;
import com.example.fintestq.service.GetYahooDataService;
import com.opensource.yfmodels.CompanyTradingInformationProto;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Path("/stocks")
@Tag(name = "Simple Stock API", description = "Simple API, demanding fundamental data for stocks")
public class StockController {

  private static final Logger LOGGER = Logger.getLogger(StockController.class);

  @Inject
  private GetYahooDataService getYahooDataService;

  @Inject
  private KafkaEmitterService kafkaEmitterService;

  @Inject
  private KafkaMessageBuilderService kafkaMessageBuilderService;

  @GET
  @Path("/{symbol}")
  @Operation(summary = "Get stock data", description = "Get stock data using the custom made yf-project")
  public YahooFullStockData getStockData(@PathParam("symbol") String symbol) {
    try {
      YahooFullStockData stock = getYahooDataService.getStock(symbol);

      handleKafkaMessaging(symbol, stock);

      return stock;
    } catch (IOException e) {
      LOGGER.infof("Application failed to get data for %s", symbol);
      return new YahooFullStockData(null, null, null, null);
    }
  }

  @GET
  @Produces("application/x-protobuf")
  @Path("/proto/{symbol}")
  @Operation(summary = "Get stock data", description = "Get stock data using the custom made yf-project")
  public Response getStockDataProto(@PathParam("symbol") String symbol) {
    try {
      YahooFullStockData stock = getYahooDataService.getStock(symbol);

      handleKafkaMessaging(symbol, stock);

      CompanyTradingInformationProto.CompanyTradingInformation tradingInformation = CompanyTradingInformationProto.CompanyTradingInformation.newBuilder()
              .setCompanyName(stock.getCompanyKeyStatistics().getCompanyName())
              .setCompanyTicker(symbol)
              .setCurrentPrice(stock.getCompanyKeyStatistics().getCurrentPrice())
              .setForwardAnnualDividendRate(stock.getCompanyKeyStatistics().getTradingInformation().getForwardAnnualDividendRate())
              .build();

      return Response.ok(tradingInformation).build();
    } catch (IOException e) {
      LOGGER.infof("Application failed to get data for %s", symbol);
      return Response.noContent().build();
    }
  }

  @GET
  @Path("/async/{symbol}")
  @Operation(summary = "Get stock data in async execution", description = "Get stock data using the custom made yf-project using asynchronous execution")
  public YahooFullStockData getStockDataAsync(@PathParam("symbol") String symbol) {
    try {
      return getYahooDataService.getStockAsync(symbol);
    } catch (ExecutionException | InterruptedException e) {
      LOGGER.infof("Application failed to get data for %s", symbol);
      return new YahooFullStockData(null, null, null, null);
    }
  }

  private void handleKafkaMessaging(String symbol, YahooFullStockData stock) {
    CompletableFuture.runAsync(() -> {
      try {
        StockData stockData = kafkaMessageBuilderService.buildStockData(stock);
        kafkaEmitterService.sendStockData(stockData);
        StockFinancials stockFinancials = kafkaMessageBuilderService.buildStockFinancials(stock);
        kafkaEmitterService.sendFinancialData(stockFinancials);
        LOGGER.infof("Kafka message sent for %s", symbol);
      } catch (Exception e) {
        LOGGER.warnf("Kafka message failed for %s: %s", symbol, e.getMessage());
      }
    });
  }

}
