package com.dutq.lock.management.lock_management.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassCodeResponse {
    private String keyboardPwd;
    private int keyboardPwdId;
}
