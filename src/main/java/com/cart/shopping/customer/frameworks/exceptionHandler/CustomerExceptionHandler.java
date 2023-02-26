package com.cart.shopping.customer.frameworks.exceptionHandler;

import com.cart.shopping.customer.businessRules.exceptions.CustomerNotFound;
import com.cart.shopping.customer.businessRules.exceptions.EmailInvalid;
import com.cart.shopping.customer.businessRules.exceptions.JDBCConnectionnFailed;
import com.cart.shopping.shared.handlingErrors.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(
            CustomerNotFound exception, WebRequest request) {

        ErrorMessage apiErrorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
        //return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }


    @ExceptionHandler(EmailInvalid.class)
    public ResponseEntity<Object> handleEmailInvalidException(
            EmailInvalid exception) {

        ErrorMessage apiErrorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
        //return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }


    @ExceptionHandler(JDBCConnectionnFailed.class)
    public ResponseEntity<Object> handleJDBCConnectionnFailedException(
            JDBCConnectionnFailed exception) {

        ErrorMessage apiErrorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
        //return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }
}