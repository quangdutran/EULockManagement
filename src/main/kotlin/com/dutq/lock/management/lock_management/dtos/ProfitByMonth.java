package com.dutq.lock.management.lock_management.dtos;

import lombok.*;

@Data
@Getter
@Setter
public class ProfitByMonth {
  private Integer year; // For example, 2024
  private Integer month; // For example, 8 for August
  private Double totalPrice;
  private Integer count;

  public ProfitByMonth(Integer year, Integer month, Double totalPrice, Integer count) {
    this.year = year;
    this.month = month;
    this.totalPrice = totalPrice;
    this.count = count;
  }

  public ProfitByMonth() {}
}
