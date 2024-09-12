package com.dutq.lock.management.lock_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public
class GetPasscodeRequest {
    private String clientId;
    private String accessToken;
    private String keyboardPwdName;
    private int keyboardPwdType;
    private int lockId;
    private long date;
    private long startDate;
    private long endDate;

}