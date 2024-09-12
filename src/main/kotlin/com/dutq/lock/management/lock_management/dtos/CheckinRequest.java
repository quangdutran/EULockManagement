package com.dutq.lock.management.lock_management.dtos;

import com.dutq.lock.management.lock_management.annotation.DateRangeValid;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@DateRangeValid
@Getter
@Setter
public class CheckinRequest {
    @NotNull
    private int lockId;
    @NotNull
    private LocalDateTime checkInTime;
    @NotNull
    private LocalDateTime checkoutTime;
    private String customerName;
    @NotNull
    private String customerPhone;
    private String customerEmail;
}
