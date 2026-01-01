import React from 'react';
import CollapsibleSection from './CollapsibleSection';
import { PriceChart } from './PriceChart';
import { TradeDataDisplay } from './TradeDataDisplay';

export const StockDataContainer = ({
  result,
  symbol,
  chartData,
  tradeData,
  onFetchTradeData,
  onFetchChart
}) => {
  if (!result) return null;

  return (
    <div style={{
      marginTop: '2rem',
      padding: '2rem',
      border: '2px solid #6400CD',
      borderRadius: '8px',
      backgroundColor: '#f9f9f9',
    }}>
      <CollapsibleSection title="Company Key Statistics" data={result.companyKeyStatistics} />
      <CollapsibleSection title="Income Statement" data={result.companyIncomeStatement} />
      <CollapsibleSection title="Cash Flow" data={result.companyCashFlow} />
      <CollapsibleSection title="Balance Sheet" data={result.companyBalanceSheet} />

      <div style={{ marginTop: '2rem', paddingTop: '2rem', borderTop: '1px solid #ddd' }}>
        <button
          type="button"
          onClick={onFetchTradeData}
          style={{
            backgroundColor: '#007BFF',
            color: 'white',
            padding: '0.5rem 1rem',
            border: 'none',
            borderRadius: '4px',
            marginBottom: '1rem',
            marginRight: '1rem',
          }}
        >
          Fetch Trade Data
        </button>

        <button
          type="button"
          onClick={onFetchChart}
          style={{
            backgroundColor: '#28A745',
            color: 'white',
            padding: '0.5rem 1rem',
            border: 'none',
            borderRadius: '4px',
            marginBottom: '1rem',
          }}
        >
          Show Price Chart
        </button>

        <TradeDataDisplay tradeData={tradeData} />
        <PriceChart chartData={chartData} symbol={symbol} />
      </div>
    </div>
  );
};