import React from 'react';

export const TradeDataDisplay = ({ tradeData }) => {
  if (!tradeData) return null;

  return (
    <div style={{ marginTop: '1rem' }}>
      <h3>Trade Data</h3>
      <pre style={{ backgroundColor: '#fff', padding: '1rem', borderRadius: '4px', border: '1px solid #ddd' }}>
        {JSON.stringify(tradeData, null, 2)}
      </pre>
    </div>
  );
};