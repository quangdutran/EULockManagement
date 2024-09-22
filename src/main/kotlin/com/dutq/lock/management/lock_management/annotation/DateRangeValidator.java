package com.dutq.lock.management.lock_management.annotation;

import com.dutq.lock.management.lock_management.dtos.CheckinRequest;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<DateRangeValid, CheckinRequest> {
  @Override
  public void initialize(DateRangeValid constraintAnnotation) {}

  @Override
  public boolean isValid(
      CheckinRequest request, ConstraintValidatorContext constraintValidatorContext) {
    if (request == null
        || Objects.isNull(request.getCheckInTime())
        || Objects.isNull(request.getCheckoutTime())) {
      return false;
    }

    LocalDateTime checkInTime = request.getCheckInTime();
    LocalDateTime checkoutTime = request.getCheckoutTime();
    return checkoutTime.isAfter(checkInTime);
  }
}
