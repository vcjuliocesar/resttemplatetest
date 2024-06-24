package com.vcjuliocesar.resttemplate.resttemplatetest.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions.CustomException;
import com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions.ProductDeleteException;
import com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions.ProductUpdateException;
import com.vcjuliocesar.resttemplate.resttemplatetest.infrastructure.util.ExceptionUtil;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CustomException.class)
    public ErrorResponse handleProductUpdateException(CustomException ex){
        ResponseStatusException responseStatusException = ExceptionUtil.getHttpException(ex);
        return new ErrorResponse(responseStatusException.getReason(), ex.getMessage());
    }

    @ExceptionHandler(ProductUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductUpdateException(ProductUpdateException ex){
        return new ErrorResponse("Product update failed", ex.getMessage());
    }

    @ExceptionHandler(ProductDeleteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductUpdateException(ProductDeleteException ex){
        return new ErrorResponse("Product delete failed", ex.getMessage());
    }

    /*@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleProductUpdateException(Exception ex){
        ResponseStatusException responseStatusException = ExceptionUtil.getHttpException(ex);
        return new ErrorResponse("Internal Server Error", responseStatusException.getReason());
    }*/

    public static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse(String error,String message){
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        
    }
}
