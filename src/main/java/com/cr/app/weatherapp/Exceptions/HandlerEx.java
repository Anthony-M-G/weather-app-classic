package com.cr.app.weatherapp.Exceptions;

import com.cr.app.weatherapp.Client.Entity.WeatherResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class HandlerEx {
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<WeatherResponse> handleRestClientException(RestClientException e) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
