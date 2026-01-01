import React, { useState } from 'react';
import { LoadingOverlay } from './LoadingOverlay';
import { SearchForm } from './SearchForm';
import { StockDataContainer } from './StockDataContainer';
import {
  StockData,
  CompanyKeyStatistics,
  CompanyIncomeStatement,
  CompanyCashFlow,
  CompanyBalanceSheet,
} from './model/StockData';

function App() {
  const [symbol, setSymbol] = useState('');
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);
  const [tradeData, setTradeData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [chartData, setChartData] = useState(null);

  const fetchStock = async () => {
    setLoading(true);
    try {
      const response = await fetch(`/stocks/v1/${symbol}`);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      const stockData = new StockData(
        new CompanyKeyStatistics(
          data.companyKeyStatistics.companyName,
          data.companyKeyStatistics.companyTicker,
          data.companyKeyStatistics.currentPrice,
          data.companyKeyStatistics.currency,
          data.companyKeyStatistics.marketCap,
          data.companyKeyStatistics.enterpriseValue,
          data.companyKeyStatistics.revenue,
          data.companyKeyStatistics.netIncomeAviToCommon,
          data.companyKeyStatistics.totalDebt,
          data.companyKeyStatistics.trailingPE,
          data.companyKeyStatistics.forwardPE,
          data.companyKeyStatistics.pegRatio,
          data.companyKeyStatistics.ratioPS,
          data.companyKeyStatistics.ratioPB,
          data.companyKeyStatistics.evToRevenue,
          data.companyKeyStatistics.evToEBITDA,
          data.companyKeyStatistics.profitMarginPercentage,
          data.companyKeyStatistics.operatingMarginPercentage,
          data.companyKeyStatistics.returnOnAssetsPercentage,
          data.companyKeyStatistics.returnOnEquityPercentage,
          data.companyKeyStatistics.revenuePerShare,
          data.companyKeyStatistics.quarterlyRevenueGrowthPercentage,
          data.companyKeyStatistics.dilutedEPS
        ),
        new CompanyIncomeStatement(
          data.companyIncomeStatement.companyName,
          data.companyIncomeStatement.companyTicker,
          data.companyIncomeStatement.currentPrice,
          data.companyIncomeStatement.totalRevenueTTM,
          data.companyIncomeStatement.pretaxIncomeTTM,
          data.companyIncomeStatement.taxProvisionTTM,
          data.companyIncomeStatement.netIncomeCommonStockholdersTTM,
          data.companyIncomeStatement.basicEPSTTM,
          data.companyIncomeStatement.dilutedEPSTTM
        ),
        new CompanyCashFlow(
          data.companyCashFlow.companyName,
          data.companyCashFlow.companyTicker,
          data.companyCashFlow.currentPrice,
          data.companyCashFlow.operatingCashFlowTTM,
          data.companyCashFlow.investingCashFlowTTM,
          data.companyCashFlow.financingCashFlowTTM,
          data.companyCashFlow.endCashPositionTTM,
          data.companyCashFlow.capitalExpenditureTTM,
          data.companyCashFlow.freeCashFlowTTM
        ),
        new CompanyBalanceSheet(
          data.companyBalanceSheet.companyName,
          data.companyBalanceSheet.companyTicker,
          data.companyBalanceSheet.currentPrice,
          data.companyBalanceSheet.totalAssets,
          data.companyBalanceSheet.totalLiabilitiesNetMinorityInterest,
          data.companyBalanceSheet.totalEquityGrossMinorityInterest,
          data.companyBalanceSheet.totalDebt,
          data.companyBalanceSheet.netDebt,
          data.companyBalanceSheet.sharesIssued
        )
      );
      setResult(stockData);
      setError(null);
    } catch (err) {
      setError(err.message);
      setResult(null);
    } finally {
      setLoading(false);
    }
  };

  const fetchTradeData = async () => {
    setLoading(true);
    try {
      const response = await fetch(`/v2/tradedata/${symbol}`);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      setTradeData(data);
      setError(null);
    } catch (err) {
      setError(err.message);
      setTradeData(null);
    } finally {
      setLoading(false);
    }
  };

  const fetchChartData = async () => {
    setLoading(true);
    try {
      const response = await fetch(`/v2/${symbol}`);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();

      const timestamps = data.chart.result[0].timestamp;
      const adjcloses = data.chart.result[0].indicators.adjclose[0].adjclose;

      const formattedData = timestamps.map((timestamp, index) => ({
        date: new Date(timestamp * 1000).toLocaleDateString(),
        price: adjcloses[index]
      }));

      setChartData(formattedData);
      setError(null);
    } catch (err) {
      setError(err.message);
      setChartData(null);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: '2rem', fontFamily: 'Arial' }}>
      <LoadingOverlay loading={loading} />

      <h2 style={{ color: '#6400CD' }}>Stock Search</h2>

      <SearchForm
        symbol={symbol}
        setSymbol={setSymbol}
        onSearch={fetchStock}
      />

      {error && <p style={{ color: 'red' }}>Error: {error}</p>}

      <StockDataContainer
        result={result}
        symbol={symbol}
        chartData={chartData}
        tradeData={tradeData}
        onFetchTradeData={fetchTradeData}
        onFetchChart={fetchChartData}
      />
    </div>
  );
}

export default App;