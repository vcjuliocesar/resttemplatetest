package com.vcjuliocesar.resttemplate.resttemplatetest.infrastructure.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions.CustomException;

public class ExceptionUtil {

    public static ResponseStatusException getHttpException(Exception exception) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        if(exception instanceof CustomException) {
            statusCode = ((CustomException) exception).getStatusCode();
        }

        return new ResponseStatusException(HttpStatus.valueOf(statusCode),exception.getMessage());
    }
}
