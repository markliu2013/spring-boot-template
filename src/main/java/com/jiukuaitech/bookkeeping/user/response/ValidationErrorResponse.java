package com.jiukuaitech.bookkeeping.user.response;

import lombok.*;
import java.util.ArrayList;
import java.util.List;


// https://reflectoring.io/bean-validation-with-spring-boot/
@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorResponse extends ErrorResponse {

    private List<Violation> violations = new ArrayList<>();

    public ValidationErrorResponse(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

}
