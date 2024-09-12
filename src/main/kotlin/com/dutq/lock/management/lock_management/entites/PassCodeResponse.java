package com.dutq.lock.management.lock_management.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PassCodeResponse {
    private String keyboardPwd;
    private String keyboardPwdId;
}
