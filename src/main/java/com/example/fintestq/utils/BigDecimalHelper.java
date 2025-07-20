package com.example.fintestq.utils;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import java.math.BigDecimal;

public class BigDecimalHelper {

  private static final Logger LOGGER = Logger.getLogger(BigDecimalHelper.class);

  private BigDecimalHelper() {
  }

  public static BigDecimal parseDouble(double value) {
    if (Double.isNaN(value) || Double.isInfinite(value)) {
      return BigDecimal.ZERO;
    }
    return BigDecimal.valueOf(value);
  }

  public static BigDecimal parseDoubleWithEndChar(String value) {
    if (StringUtils.isBlank(value)) {
      return BigDecimal.ZERO;
    }

    value = value.trim().toUpperCase();
    long multiplier = 1L;


    if (value.endsWith("M")) {
      multiplier = 1000000L;
      value = value.substring(0, value.length() - 1);
    } else if (value.endsWith("B")) {
      multiplier = 1000000000L;
      value = value.substring(0, value.length() - 1);
    } else if (value.endsWith("T")) {
      multiplier = 1000000000000L;
      value = value.substring(0, value.length() - 1);
    }
    try {
      double numericValue = Double.parseDouble(value);
      return BigDecimal.valueOf(numericValue * multiplier);
    } catch (NumberFormatException e) {
      LOGGER.infof("Application failed to convert data data for %s", value);
    }

    return BigDecimal.ZERO;
  }
}
