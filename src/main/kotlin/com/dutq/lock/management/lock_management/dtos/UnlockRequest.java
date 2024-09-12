package com.dutq.lock.management.lock_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UnlockRequest {
    private String clientId;
    private String accessToken;
    private int lockId;
    private long date;
}