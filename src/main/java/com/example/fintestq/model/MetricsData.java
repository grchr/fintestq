package com.example.fintestq.model;

import java.math.BigDecimal;

public class MetricsData {

  private final BigDecimal freeCashFlowYield;
  private final BigDecimal netDebtToEbitdaRatio;
  private final BigDecimal returnOnInvestedCapital;
  private final BigDecimal freeCashFlowMargin;
  private final BigDecimal enterpriseValueToFreeCashFlow;
  private final BigDecimal earningsYieldRatio;
  private final BigDecimal debtToEquityRatio;
  private final BigDecimal cashToLiabilitiesRatio;
  private final BigDecimal revenuePerOutstandingShare;
  private final BigDecimal profitabilityScore;

  private MetricsData(Builder builder) {
    this.freeCashFlowYield = builder.freeCashFlowYield;
    this.netDebtToEbitdaRatio = builder.netDebtToEbitdaRatio;
    this.returnOnInvestedCapital = builder.returnOnInvestedCapital;
    this.freeCashFlowMargin = builder.freeCashFlowMargin;
    this.enterpriseValueToFreeCashFlow = builder.enterpriseValueToFreeCashFlow;
    this.earningsYieldRatio = builder.earningsYieldRatio;
    this.debtToEquityRatio = builder.debtToEquityRatio;
    this.cashToLiabilitiesRatio = builder.cashToLiabilitiesRatio;
    this.revenuePerOutstandingShare = builder.revenuePerOutstandingShare;
    this.profitabilityScore = builder.profitabilityScore;
  }

  public static class Builder {
    private BigDecimal freeCashFlowYield;
    private BigDecimal netDebtToEbitdaRatio;
    private BigDecimal returnOnInvestedCapital;
    private BigDecimal freeCashFlowMargin;
    private BigDecimal enterpriseValueToFreeCashFlow;
    private BigDecimal earningsYieldRatio;
    private BigDecimal debtToEquityRatio;
    private BigDecimal cashToLiabilitiesRatio;
    private BigDecimal revenuePerOutstandingShare;
    private BigDecimal profitabilityScore;

    public Builder withFreeCashFlowYield(BigDecimal value) {
      this.freeCashFlowYield = value;
      return this;
    }

    public Builder withNetDebtToEbitdaRatio(BigDecimal value) {
      this.netDebtToEbitdaRatio = value;
      return this;
    }

    public Builder withReturnOnInvestedCapital(BigDecimal value) {
      this.returnOnInvestedCapital = value;
      return this;
    }

    public Builder withFreeCashFlowMargin(BigDecimal value) {
      this.freeCashFlowMargin = value;
      return this;
    }

    public Builder withEnterpriseValueToFreeCashFlow(BigDecimal value) {
      this.enterpriseValueToFreeCashFlow = value;
      return this;
    }

    public Builder withEarningsYieldRatio(BigDecimal value) {
      this.earningsYieldRatio = value;
      return this;
    }

    public Builder withDebtToEquityRatio(BigDecimal value) {
      this.debtToEquityRatio = value;
      return this;
    }

    public Builder withCashToLiabilitiesRatio(BigDecimal value) {
      this.cashToLiabilitiesRatio = value;
      return this;
    }

    public Builder withRevenuePerOutstandingShare(BigDecimal value) {
      this.revenuePerOutstandingShare = value;
      return this;
    }

    public Builder withProfitabilityScore(BigDecimal value) {
      this.profitabilityScore = value;
      return this;
    }

    public MetricsData build() {
      return new MetricsData(this);
    }
  }

  // Getters (optional, can be generated with Lombok if desired)
  public BigDecimal getFreeCashFlowYield() {
    return freeCashFlowYield;
  }

  public BigDecimal getNetDebtToEbitdaRatio() {
    return netDebtToEbitdaRatio;
  }

  public BigDecimal getReturnOnInvestedCapital() {
    return returnOnInvestedCapital;
  }

  public BigDecimal getFreeCashFlowMargin() {
    return freeCashFlowMargin;
  }

  public BigDecimal getEnterpriseValueToFreeCashFlow() {
    return enterpriseValueToFreeCashFlow;
  }

  public BigDecimal getEarningsYieldRatio() {
    return earningsYieldRatio;
  }

  public BigDecimal getDebtToEquityRatio() {
    return debtToEquityRatio;
  }

  public BigDecimal getCashToLiabilitiesRatio() {
    return cashToLiabilitiesRatio;
  }

  public BigDecimal getRevenuePerOutstandingShare() {
    return revenuePerOutstandingShare;
  }

  public BigDecimal getProfitabilityScore() {
    return profitabilityScore;
  }
}
