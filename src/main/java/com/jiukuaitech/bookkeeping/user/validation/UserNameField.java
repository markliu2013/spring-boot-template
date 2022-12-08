package com.jiukuaitech.bookkeeping.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.*;

import java.lang.annotation.*;

@NotBlank
@Size(min = 1, max = 16)
@Pattern(regexp = "^[A-Za-z0-9]*$") //用户名只允许数字加字母

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameField {

    String message() default "{valid.fail}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

