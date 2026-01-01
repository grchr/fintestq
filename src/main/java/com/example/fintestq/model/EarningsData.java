package com.example.fintestq.model;

import java.util.List;

public class EarningsData {

  private List<Integer> earningsTimestamps;
  private List<Double> earningsValues;

  public EarningsData(List<Integer> earningsTimestamps, List<Double> earningsValues) {
    this.earningsTimestamps = earningsTimestamps;
    this.earningsValues = earningsValues;
  }

  public List<Integer> getEarningsTimestamps() {
    return earningsTimestamps;
  }

  public List<Double> getEarningsValues() {
    return earningsValues;
  }

}
