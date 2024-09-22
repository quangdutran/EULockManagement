package com.dutq.lock.management.lock_management.dtos.api;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ErrorResponse {
  private int errcode;
  private String errmsg;
  private String description;
}
