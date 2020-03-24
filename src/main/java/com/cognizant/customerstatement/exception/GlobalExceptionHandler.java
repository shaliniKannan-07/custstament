package com.cognizant.customerstatement.exception;

import com.cognizant.customerstatement.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.cognizant.customerstatement.utility.Constant.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonParsingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleJsonParsingException(final Throwable ex) {
        log.info("bad request", ex);
        return new ErrorResponse(BAD_REQUEST, "Error Occured during Json Parsing");
    }
}
