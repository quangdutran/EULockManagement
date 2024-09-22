package com.dutq.lock.management.lock_management.entites;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
public class LockListResponse {
  private List<LockItem> list;
  private int pageNo;
  private int pageSize;
  private int pages;
  private int total;
}
