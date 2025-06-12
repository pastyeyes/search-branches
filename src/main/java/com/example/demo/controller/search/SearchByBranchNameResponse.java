package com.example.demo.controller.search;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonInclude;

public record SearchByBranchNameResponse(short code, String name, @JsonInclude(JsonInclude.Include.NON_NULL) BigDecimal cost) {
    // Ensures the cost is formatted to two decimal places, rounding up.
    public SearchByBranchNameResponse {
      if (cost != null) {
          cost = cost.setScale(2, RoundingMode.HALF_UP);
      }
  }
}
