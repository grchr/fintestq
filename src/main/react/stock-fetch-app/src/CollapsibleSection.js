// src/CollapsibleSection.js
import { useState } from "react";

const LABELS = {
  companyName: 'Company Name',
  companyTicker: 'Ticker',
  currentPrice: 'Current Price',
  currency: 'Currency',
  marketCap: 'Market Cap',
  enterpriseValue: 'Enterprise Value',
  revenue: 'Revenue',
  netIncomeAviToCommon: 'Net Income',
  totalDebt: 'Total Debt',
  trailingPE: 'Trailing P/E',
  forwardPE: 'Forward P/E',
  pegRatio: 'PEG Ratio',
  ratioPS: 'Price/Sales Ratio',
  ratioPB: 'Price/Book Ratio',
  evToRevenue: 'EV/Revenue',
  evToEBITDA: 'EV/EBITDA',
  profitMarginPercentage: 'Profit Margin (%)',
  operatingMarginPercentage: 'Operating Margin (%)',
  returnOnAssetsPercentage: 'Return on Assets (%)',
  returnOnEquityPercentage: 'Return on Equity (%)',
  revenuePerShare: 'Revenue per Share',
  quarterlyRevenueGrowthPercentage: 'Quarterly Revenue Growth (%)',
  dilutedEPS: 'Diluted EPS',
  totalRevenueTTM: 'Total Revenue (TTM)',
  pretaxIncomeTTM: 'Pretax Income (TTM)',
  taxProvisionTTM: 'Tax Provision (TTM)',
  netIncomeCommonStockholdersTTM: 'Net Income (TTM)',
  basicEPSTTM: 'Basic EPS (TTM)',
  dilutedEPSTTM: 'Diluted EPS (TTM)',
  operatingCashFlowTTM: 'Operating Cash Flow (TTM)',
  investingCashFlowTTM: 'Investing Cash Flow (TTM)',
  financingCashFlowTTM: 'Financing Cash Flow (TTM)',
  endCashPositionTTM: 'End Cash Position (TTM)',
  capitalExpenditureTTM: 'Capital Expenditure (TTM)',
  freeCashFlowTTM: 'Free Cash Flow (TTM)',
  totalAssets: 'Total Assets',
  totalLiabilitiesNetMinorityInterest: 'Total Liabilities',
  totalEquityGrossMinorityInterest: 'Total Equity',
  netDebt: 'Net Debt',
  sharesIssued: 'Shares Issued',
};

function CollapsibleSection({ title, data }) {
  const [open, setOpen] = useState(false);

  if (!data) return null;

  return (
    <div style={{ marginBottom: "1.5rem", border: "1px solid #ddd", borderRadius: "8px" }}>
      <div
        onClick={() => setOpen(!open)}
        style={{
          backgroundColor: "#6400CD",
          color: "white",
          padding: "0.75rem 1rem",
          cursor: "pointer",
          borderRadius: "8px 8px 0 0",
        }}
      >
        <strong>{title}</strong> {open ? "▲" : "▼"}
      </div>

      {open && (
              <div style={{ padding: "1rem", backgroundColor: "#fafafa" }}>
                <table style={{ width: "100%", borderCollapse: "collapse" }}>
                  <tbody>
                    {Object.entries(data).map(([key, value]) => (
                      <tr key={key}>
                        <td
                          style={{
                            borderBottom: "1px solid #ddd",
                            padding: "0.5rem",
                            fontWeight: "bold",
                            width: "40%",
                          }}
                        >
                          {LABELS[key] || key}
                        </td>
                        <td style={{ borderBottom: "1px solid #ddd", padding: "0.5rem" }}>
                          {String(value)}
                        </td>
                      </tr>
                    ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default CollapsibleSection;
