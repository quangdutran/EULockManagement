package com.dutq.lock.management.lock_management.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRangeValid {
  String message() default "Invalid date range";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
