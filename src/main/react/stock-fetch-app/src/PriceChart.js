import React from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

export const PriceChart = ({ chartData, symbol }) => {
  if (!chartData) return null;

  return (
    <div style={{ marginTop: '1rem' }}>
      <h3>Price History</h3>
      <ResponsiveContainer width="100%" height={400}>
        <LineChart data={chartData} margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis
            dataKey="date"
            angle={-45}
            textAnchor="end"
            height={80}
            tick={{ fontSize: 12 }}
          />
          <YAxis
            label={{ value: 'Price', angle: -90, position: 'insideLeft' }}
            tick={{ fontSize: 12 }}
            domain={['auto', 'auto']}
          />
          <Tooltip
            formatter={(value) => `$${Number(value).toFixed(2)}`}
            labelStyle={{ color: '#333' }}
          />
          <Legend />
          <Line
            type="monotone"
            dataKey="price"
            stroke="#6400CD"
            strokeWidth={2}
            dot={false}
            name={`${symbol.toUpperCase()} Price`}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};