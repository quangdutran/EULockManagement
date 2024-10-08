package com.dutq.lock.management.lock_management.dtos;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

  @NotEmpty private String firstName;

  @NotEmpty private String lastName;

  @NotEmpty private String login;

  @NotEmpty private char[] password;
}
