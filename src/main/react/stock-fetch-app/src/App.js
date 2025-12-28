import logo from './logo.svg';
import React, { useState } from 'react';
import CollapsibleSection from './CollapsibleSection';
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

  const fetchStock = async () => {
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
    }
  };

  const fetchTradeData = async () => {
    try {
      const response = await fetch(`/stocks/v2/tradedata/${symbol}`);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      setTradeData(data); // Store the result in state
      setError(null);
    } catch (err) {
      setError(err.message);
      setTradeData(null);
    }
  };

  return (
      <div style={{ padding: '2rem', fontFamily: 'Arial' }}>
        <h2 style={{ color: '#6400CD' }}>Stock Search</h2>

        <form
          onSubmit={(e) => {
            e.preventDefault();
            fetchStock();
          }}
        >
          <input
            type="text"
            placeholder="Enter symbol (e.g. AAPL)"
            value={symbol}
            onChange={(e) => setSymbol(e.target.value)}
            style={{ marginRight: '1rem', padding: '0.5rem' }}
          />
          <button
            type="submit"
            style={{
              backgroundColor: '#6400CD',
              color: 'white',
              padding: '0.5rem 1rem',
              border: 'none',
              borderRadius: '4px',
            }}
          >
            Search
          </button>
          <button
                    type="button"
                    onClick={fetchTradeData}
                    style={{
                      backgroundColor: '#007BFF',
                      color: 'white',
                      padding: '0.5rem 1rem',
                      border: 'none',
                      borderRadius: '4px',
                    }}
                  >
                    Fetch Trade Data
                  </button>
        </form>

        {error && <p style={{ color: 'red' }}>Error: {error}</p>}

        {result && (
          <div style={{ marginTop: '2rem' }}>
            <CollapsibleSection title="Company Key Statistics" data={result.companyKeyStatistics} />
            <CollapsibleSection title="Income Statement" data={result.companyIncomeStatement} />
            <CollapsibleSection title="Cash Flow" data={result.companyCashFlow} />
            <CollapsibleSection title="Balance Sheet" data={result.companyBalanceSheet} />
          </div>
        )}
              {tradeData && (
                <div style={{ marginTop: '2rem' }}>
                  <h3>Trade Data</h3>
                  <pre style={{ backgroundColor: '#f4f4f4', padding: '1rem' }}>
                    {JSON.stringify(tradeData, null, 2)}
                  </pre>
                </div>
              )}
      </div>
    );
  }

  export default App;

