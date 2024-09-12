package com.dutq.lock.management.lock_management.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenResponse {
    private String access_token;
    private String refresh_token;
    private int expires_in;
    private long openid;
    private String scope;
    private String token_type;
}
