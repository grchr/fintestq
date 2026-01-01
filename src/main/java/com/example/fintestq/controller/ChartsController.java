package com.example.fintestq.controller;

import com.example.fintestq.model.YahooFullStockData;
import com.example.fintestq.service.GetYahooChartDataServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;
import org.opensource.exceptions.YahooServiceException;
import org.opensource.model.response.charts.YahooEventChart;

@Path("/charts")
@Tag(name = "Charts API", description = "API for retrieving chart data")
public class ChartsController {

  private static final Logger LOGGER = Logger.getLogger(ChartsController.class);

  @Inject
  private GetYahooChartDataServiceImpl getYahooChartDataService;

  @GET
  @Path("/v2/{symbol}")
  @Operation(summary = "Get stock chart data", description = "Get stock chart data using the custom made yf-project")
  public YahooEventChart getChartData(@PathParam("symbol") String symbol) {
    try {
      return getYahooChartDataService.getChartData(symbol);

    } catch (YahooServiceException e) {
      LOGGER.infof("Application failed to get chart data for %s", symbol);
      return new YahooEventChart();
    }
  }
}
