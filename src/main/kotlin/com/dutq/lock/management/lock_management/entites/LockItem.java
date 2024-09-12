package com.dutq.lock.management.lock_management.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LockItem {
    private long date;
    private long specialValue;
    private String lockAlias;
    private String noKeyPwd;
    private long electricQuantityUpdateDate;
    private String lockMac;
    private int passageMode;
    private int timezoneRawOffset;
    private int lockId;
    private String featureValue;
    private int electricQuantity;
    private long bindDate;
    private String lockData;
    private int hasGateway;
    private int keyboardPwdVersion;
    private String wirelessKeypadFeatureValue;
    private LockVersion lockVersion;
    private String lockName;
}
