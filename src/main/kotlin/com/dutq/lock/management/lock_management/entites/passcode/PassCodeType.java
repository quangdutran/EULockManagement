package com.dutq.lock.management.lock_management.entites.passcode;

public enum PassCodeType {
  PERMANENT(2),
  PERIOD(3);

  private final int value;

  PassCodeType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static PassCodeType fromValue(int value) {
    for (PassCodeType status : PassCodeType.values()) {
      if (status.getValue() == value) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown value: " + value);
  }
}
