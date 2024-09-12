package com.dutq.lock.management.lock_management.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewTokenResponse extends RefreshTokenResponse {
    private int uid;
}
