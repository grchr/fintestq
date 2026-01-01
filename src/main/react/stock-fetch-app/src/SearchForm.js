import React from 'react';

export const SearchForm = ({ symbol, setSymbol, onSearch }) => {
  return (
    <form
      onSubmit={(e) => {
        e.preventDefault();
        onSearch();
      }}
    >
      <input
        type="text"
        placeholder="Enter symbol (e.g. AAPL, BNP.PA)"
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
    </form>
  );
};