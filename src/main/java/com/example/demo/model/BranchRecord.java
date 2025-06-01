package com.example.demo.model;

import java.math.BigDecimal;

/**
 * Represents a branch with a code, a name, and running cost.
 * name and cost can be missing in the datasource.
 */
public record BranchRecord(short code, String name, BigDecimal cost) {}
