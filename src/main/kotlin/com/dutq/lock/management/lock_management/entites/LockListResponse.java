package com.dutq.lock.management.lock_management.entites;

import lombok.*;

import java.util.List;

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
