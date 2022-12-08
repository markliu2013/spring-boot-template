package com.jiukuaitech.bookkeeping.user.exception;

import com.jiukuaitech.bookkeeping.user.response.*;
import com.jiukuaitech.bookkeeping.user.utils.MessageSourceUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


// https://www.baeldung.com/rest-api-error-handling-best-practices
@RestControllerAdvice
public class GlobalExceptionHandler {

    private MessageSourceUtil messageSourceUtil;


    // 输入验证没通过 post的body
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse exceptionHandler(MethodArgumentNotValidException e) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setErrorMessage(messageSourceUtil.getMessage("valid.fail"));
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            response.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return response;
    }

    // 输入验证没通过 query parameters. variables within the path
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    ValidationErrorResponse exceptionHandler(ConstraintViolationException e) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setErrorMessage(messageSourceUtil.getMessage("valid.fail"));
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            response.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return response;
    }

    // entity 类上面的验证
    // https://stackoverflow.com/questions/45070642/springboot-doesnt-handle-org-hibernate-exception-constraintviolationexception
    // https://stackoverflow.com/questions/40808319/hibernate-validation-results-in-spring-transactionsystemexception
    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public BaseResponse exceptionHandler(TransactionSystemException e) {
        if (e.getRootCause() instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getRootCause();
            ValidationErrorResponse response = new ValidationErrorResponse(4221, messageSourceUtil.getMessage("valid.fail"));
            for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
                response.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
            }
            return response;
        } else {
            // TODO
            e.printStackTrace();
            return new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage());
        }
    }

    // 最后一层database的验证，长度，unique约束等
    // https://stackoverflow.com/questions/2109476/how-to-handle-dataintegrityviolationexception-in-spring
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    public BaseResponse exceptionHandler(DataIntegrityViolationException e) {
        String rootMsg = "";
        if (e.getRootCause() != null) {
            rootMsg = e.getRootCause().getMessage();
        }
        return new ErrorResponse(HttpStatus.CONFLICT.value(), rootMsg);
    }

    @ExceptionHandler(ItemExistsException.class)
//    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ResponseBody
    public BaseResponse exceptionHandler(ItemExistsException e) {
        String errMsg;
        if (StringUtils.hasText(e.getMessage())) {
            errMsg = messageSourceUtil.getMessage(e.getMessage());
        } else {
            errMsg = messageSourceUtil.getMessage("item.exists.exception");
        }
        return new ErrorResponse(HttpStatus.CONFLICT.value(), errMsg);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseBody  // 404
    public BaseResponse exceptionHandler(ItemNotFoundException e) {
        String errMsg;
        if (StringUtils.hasText(e.getMessage())) {
            errMsg = messageSourceUtil.getMessage(e.getMessage());
        } else {
            errMsg = messageSourceUtil.getMessage("item.not.found.exception");
        }
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), errMsg);
    }

}
