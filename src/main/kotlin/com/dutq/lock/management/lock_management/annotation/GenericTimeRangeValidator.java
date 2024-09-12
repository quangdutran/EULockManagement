package com.dutq.lock.management.lock_management.annotation;

import com.dutq.lock.management.lock_management.dtos.TimeRangeRequest;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenericTimeRangeValidator
    implements ConstraintValidator<GenericTimeRangeValid, TimeRangeRequest> {
  @Override
  public void initialize(GenericTimeRangeValid constraintAnnotation) {}

  @Override
  public boolean isValid(
      TimeRangeRequest request, ConstraintValidatorContext constraintValidatorContext) {
    if (request == null || Objects.isNull(request.getFrom()) || Objects.isNull(request.getTo())) {
      return false;
    }

    LocalDateTime from = request.getFrom();
    LocalDateTime to = request.getTo();
    return to.isAfter(from);
  }
}
