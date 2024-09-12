package com.dutq.lock.management.lock_management.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LockDTO {
    private String lockId;
    private String lockAlias;
    private int electricQuantity;
}
