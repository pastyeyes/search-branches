package com.example.demo.controller.search;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record SearchByNameResponse(short code, String name, BigDecimal cost) {

    // Ensures the cost is formatted to two decimal places, rounding up.
    public SearchByNameResponse {
      if (cost != null) {
          cost = cost.setScale(2, RoundingMode.HALF_UP);
      }
  }
}
