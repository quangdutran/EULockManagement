package com.dutq.lock.management.lock_management.dtos;

import com.dutq.lock.management.lock_management.annotation.GenericTimeRangeValid;
import java.time.LocalDateTime;
import lombok.*;

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
