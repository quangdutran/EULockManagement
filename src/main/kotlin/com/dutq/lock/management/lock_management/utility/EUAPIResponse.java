package com.dutq.lock.management.lock_management.utility;

import com.dutq.lock.management.lock_management.dtos.api.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EUAPIResponse<T> {
    private Optional<ErrorResponse> error;
    private Optional<T> data;
}
