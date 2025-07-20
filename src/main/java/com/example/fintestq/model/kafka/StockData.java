package com.example.fintestq.model.kafka;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockData {

  private String symbol;
  private double price;
  private String currency;

}
