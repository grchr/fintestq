package com.example.fintestq.kafka.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockFinancials {

  private String symbol;
  private double totalDebt;
  private double freeCashFlowTTM;

}
