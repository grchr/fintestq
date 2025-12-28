package com.example.fintestq.controller;

import com.example.fintestq.exceptions.BusinessException;
import com.example.fintestq.model.MetricsData;
import com.example.fintestq.service.ComputationalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

@Path("/metrics")
@Tag(name = "Simple Stock API", description = "Simple API, demanding fundamental data for stocks")
public class MetricsController {

  private static final Logger LOGGER = Logger.getLogger(MetricsController.class);

  @Inject
  private ComputationalService computationalService;

  @GET
  @Path("/v1/{symbol}")
  @Operation(summary = "Compute stock metrics data", description = "Compute and get metrics calculated using the custom made yf-project")
  public MetricsData getMetricsData(@PathParam("symbol") String symbol) {
    try {
      return computationalService.getFullComputationData(symbol);
    } catch (BusinessException e) {
      LOGGER.infof("Application failed to compute data for %s", symbol);
      return new MetricsData.Builder().build();
    }
  }
}
