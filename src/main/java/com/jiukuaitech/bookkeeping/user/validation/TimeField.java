package com.jiukuaitech.bookkeeping.user.validation;

import jakarta.validation.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.lang.annotation.*;


@Min(0)
@Max(99999999999999L)

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeField {

    String message() default "{valid.fail}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

