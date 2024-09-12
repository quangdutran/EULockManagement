package com.dutq.lock.management.lock_management.dtos;

import com.dutq.lock.management.lock_management.annotation.DateRangeValid;
import com.dutq.lock.management.lock_management.annotation.GenericTimeRangeValid;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@GenericTimeRangeValid
public class TimeRangeRequest {
    private LocalDateTime from;
    private LocalDateTime to;
}
