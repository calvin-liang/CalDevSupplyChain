package com.caldevsupplychain.common.exception;

import com.caldevsupplychain.common.ws.account.ApiErrorsWS;
import com.caldevsupplychain.common.ws.account.ErrorWS;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;


@ControllerAdvice
public class ApiErrorsExceptionHandler extends ResponseEntityExceptionHandler {

    public List<ErrorWS> generateErrorWSList(BindingResult errors) {
        return errors.getFieldErrors()
                .stream()
                .map(error -> new ErrorWS (
                        error.getCode(),
                        error.getDefaultMessage()
                ))
                .collect(toList());
    }
}