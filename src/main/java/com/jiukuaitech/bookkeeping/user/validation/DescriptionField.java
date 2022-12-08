package com.jiukuaitech.bookkeeping.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;


@Size(min = 1, max = 32)
@NotStartsWithSpace
@NotEndsWithSpace

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DescriptionField {

    String message() default "{valid.fail}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

