package com.example.fintestq.controller;

import com.example.fintestq.model.YahooFullStockData;
import com.example.fintestq.service.GetYahooDataService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import java.io.IOException;

@Path("/stocks")
@Tag(name = "Simple Stock API", description = "Simple API, demanding fundamental data for stocks")
public class StockController {

  private static final Logger LOGGER = Logger.getLogger(StockController.class);

  @Inject
  private GetYahooDataService getYahooDataService;

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

}
