package com.example.fintestq.model;

import org.opensource.model.CompanyBalanceSheet;
import org.opensource.model.CompanyCashFlow;
import org.opensource.model.CompanyIncomeStatement;
import org.opensource.model.CompanyKeyStatistics;

public class YahooFullStockData {

  private CompanyKeyStatistics companyKeyStatistics;

  private CompanyBalanceSheet companyBalanceSheet;

  private CompanyCashFlow companyCashFlow;

  private CompanyIncomeStatement companyIncomeStatement;

  public YahooFullStockData(CompanyKeyStatistics companyKeyStatistics, CompanyBalanceSheet companyBalanceSheet, CompanyCashFlow companyCashFlow, CompanyIncomeStatement companyIncomeStatement) {
    this.companyKeyStatistics = companyKeyStatistics;
    this.companyBalanceSheet = companyBalanceSheet;
    this.companyCashFlow = companyCashFlow;
    this.companyIncomeStatement = companyIncomeStatement;
  }

  public CompanyKeyStatistics getCompanyKeyStatistics() {
    return companyKeyStatistics;
  }

  public void setCompanyKeyStatistics(CompanyKeyStatistics companyKeyStatistics) {
    this.companyKeyStatistics = companyKeyStatistics;
  }

  public CompanyBalanceSheet getCompanyBalanceSheet() {
    return companyBalanceSheet;
  }

  public void setCompanyBalanceSheet(CompanyBalanceSheet companyBalanceSheet) {
    this.companyBalanceSheet = companyBalanceSheet;
  }

  public CompanyCashFlow getCompanyCashFlow() {
    return companyCashFlow;
  }

  public void setCompanyCashFlow(CompanyCashFlow companyCashFlow) {
    this.companyCashFlow = companyCashFlow;
  }

  public CompanyIncomeStatement getCompanyIncomeStatement() {
    return companyIncomeStatement;
  }

  public void setCompanyIncomeStatement(CompanyIncomeStatement companyIncomeStatement) {
    this.companyIncomeStatement = companyIncomeStatement;
  }
}
