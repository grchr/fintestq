import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {
  const [symbol, setSymbol] = useState('');
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const fetchStock = async () => {
    try {
      const response = await fetch(`/stocks/${symbol}`);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      setResult(data);
      setError(null);
    } catch (err) {
      setError(err.message);
      setResult(null);
    }
  };

  return (
    <div style={{ padding: '2rem', fontFamily: 'Arial' }}>
      <h2>Stock Search</h2>
      <h3>Under construction</h3>
      <form
            onSubmit={(e) => {
              e.preventDefault(); // Prevent page reload
              fetchStock();       // Call the same function as the button
            }}
          >
            <input
              type="text"
              placeholder="Enter symbol (e.g. AAPL)"
              value={symbol}
              onChange={(e) => setSymbol(e.target.value)}
              style={{ marginRight: '1rem' }}
            />
            <button type="submit">Search</button>
          </form>

      {error && <p style={{ color: 'red' }}>Error: {error}</p>}

      {result && (
        <pre style={{ marginTop: '1rem', background: '#eee', padding: '1rem' }}>
          {JSON.stringify(result, null, 2)}
        </pre>
      )}
    </div>
  );
}

export default App;

