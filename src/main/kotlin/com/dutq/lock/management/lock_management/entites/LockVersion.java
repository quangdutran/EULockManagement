package com.dutq.lock.management.lock_management.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LockVersion {
  private boolean showAdminKbpwdFlag;
  private int groupId;
  private int protocolVersion;
  private int protocolType;
  private int orgId;
  private String logoUrl;
  private int scene;
}
