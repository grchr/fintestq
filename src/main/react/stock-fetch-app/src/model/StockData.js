// Company Key Statistics
export class CompanyKeyStatistics {
  constructor(
    companyName,
    companyTicker,
    currentPrice,
    currency,
    marketCap,
    enterpriseValue,
    revenue,
    netIncomeAviToCommon,
    totalDebt,
    trailingPE,
    forwardPE,
    pegRatio,
    ratioPS,
    ratioPB,
    evToRevenue,
    evToEBITDA,
    profitMarginPercentage,
    operatingMarginPercentage,
    returnOnAssetsPercentage,
    returnOnEquityPercentage,
    revenuePerShare,
    quarterlyRevenueGrowthPercentage,
    dilutedEPS
  ) {
    this.companyName = companyName;
    this.companyTicker = companyTicker;
    this.currentPrice = currentPrice;
    this.currency = currency;
    this.marketCap = marketCap;
    this.enterpriseValue = enterpriseValue;
    this.revenue = revenue;
    this.netIncomeAviToCommon = netIncomeAviToCommon;
    this.totalDebt = totalDebt;
    this.trailingPE = trailingPE;
    this.forwardPE = forwardPE;
    this.pegRatio = pegRatio;
    this.ratioPS = ratioPS;
    this.ratioPB = ratioPB;
    this.evToRevenue = evToRevenue;
    this.evToEBITDA = evToEBITDA;
    this.profitMarginPercentage = profitMarginPercentage;
    this.operatingMarginPercentage = operatingMarginPercentage;
    this.returnOnAssetsPercentage = returnOnAssetsPercentage;
    this.returnOnEquityPercentage = returnOnEquityPercentage;
    this.revenuePerShare = revenuePerShare;
    this.quarterlyRevenueGrowthPercentage = quarterlyRevenueGrowthPercentage;
    this.dilutedEPS = dilutedEPS;
  }
}

// Company Income Statement
export class CompanyIncomeStatement {
  constructor(
    companyName,
    companyTicker,
    currentPrice,
    totalRevenueTTM,
    pretaxIncomeTTM,
    taxProvisionTTM,
    netIncomeCommonStockholdersTTM,
    basicEPSTTM,
    dilutedEPSTTM
  ) {
    this.companyName = companyName;
    this.companyTicker = companyTicker;
    this.currentPrice = currentPrice;
    this.totalRevenueTTM = totalRevenueTTM;
    this.pretaxIncomeTTM = pretaxIncomeTTM;
    this.taxProvisionTTM = taxProvisionTTM;
    this.netIncomeCommonStockholdersTTM = netIncomeCommonStockholdersTTM;
    this.basicEPSTTM = basicEPSTTM;
    this.dilutedEPSTTM = dilutedEPSTTM;
  }
}

// Company Cash Flow
export class CompanyCashFlow {
  constructor(
    companyName,
    companyTicker,
    currentPrice,
    operatingCashFlowTTM,
    investingCashFlowTTM,
    financingCashFlowTTM,
    endCashPositionTTM,
    capitalExpenditureTTM,
    freeCashFlowTTM
  ) {
    this.companyName = companyName;
    this.companyTicker = companyTicker;
    this.currentPrice = currentPrice;
    this.operatingCashFlowTTM = operatingCashFlowTTM;
    this.investingCashFlowTTM = investingCashFlowTTM;
    this.financingCashFlowTTM = financingCashFlowTTM;
    this.endCashPositionTTM = endCashPositionTTM;
    this.capitalExpenditureTTM = capitalExpenditureTTM;
    this.freeCashFlowTTM = freeCashFlowTTM;
  }
}

// Company Balance Sheet
export class CompanyBalanceSheet {
  constructor(
    companyName,
    companyTicker,
    currentPrice,
    totalAssets,
    totalLiabilitiesNetMinorityInterest,
    totalEquityGrossMinorityInterest,
    totalDebt,
    netDebt,
    sharesIssued
  ) {
    this.companyName = companyName;
    this.companyTicker = companyTicker;
    this.currentPrice = currentPrice;
    this.totalAssets = totalAssets;
    this.totalLiabilitiesNetMinorityInterest = totalLiabilitiesNetMinorityInterest;
    this.totalEquityGrossMinorityInterest = totalEquityGrossMinorityInterest;
    this.totalDebt = totalDebt;
    this.netDebt = netDebt;
    this.sharesIssued = sharesIssued;
  }
}

// Aggregate class
export class StockData {
  constructor(
    companyKeyStatistics,
    companyIncomeStatement,
    companyCashFlow,
    companyBalanceSheet
  ) {
    this.companyKeyStatistics = companyKeyStatistics;
    this.companyIncomeStatement = companyIncomeStatement;
    this.companyCashFlow = companyCashFlow;
    this.companyBalanceSheet = companyBalanceSheet;
  }
}
