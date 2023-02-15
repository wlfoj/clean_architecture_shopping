package com.cart.shopping.product.frameworks.exceptionHandler;

import com.cart.shopping.product.businessRules.exceptions.NegativePrice;
import com.cart.shopping.product.businessRules.exceptions.ProductNotFound;
import com.cart.shopping.shared.handlingErrors.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Object> handleProductNotFoundException(
            ProductNotFound exception, WebRequest request) {

        ErrorMessage apiErrorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
        //return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }


    @ExceptionHandler(NegativePrice.class)
    public ResponseEntity<Object> handleNegativePriceException(
            NegativePrice exception) {

        ErrorMessage apiErrorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
        //return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }
}
