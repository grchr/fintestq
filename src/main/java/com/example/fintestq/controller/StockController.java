package com.example.fintestq.controller;

import com.example.fintestq.model.YahooFullStockData;
import com.example.fintestq.model.kafka.StockData;
import com.example.fintestq.model.kafka.StockFinancials;
import com.example.fintestq.service.IGetYahooStockDataService;
import com.example.fintestq.service.ProtobufBuilderService;
import com.example.fintestq.service.kafka.KafkaEmitterService;
import com.example.fintestq.service.kafka.KafkaMessageBuilderService;
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
  private IGetYahooStockDataService getYahooDataService;

  @Inject
  private KafkaEmitterService kafkaEmitterService;

  @Inject
  private KafkaMessageBuilderService kafkaMessageBuilderService;

  @Inject
  private ProtobufBuilderService protobufBuilderService;

  @GET
  @Path("/{symbol}")
  @Operation(summary = "Get stock data", description = "Get stock data using the custom made yf-project")
  public YahooFullStockData getStockData(@PathParam("symbol") String symbol) {
    try {
      return getYahooDataService.getStock(symbol);
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
      CompanyTradingInformationProto.CompanyTradingInformation tradingInformation = protobufBuilderService.buildTradingInfoData(stock);
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

}
