package com.dutq.lock.management.lock_management.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String login;
  private String token;
}
