package com.example.volunteerappbackend.Donation.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class DonationsAdvice {
    @ResponseBody
    @ExceptionHandler(AddDonationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String AddDonationExceptionHandler(AddDonationException ex) {
        return ex.getMessage();

    }

    @ResponseBody
    @ExceptionHandler(ReceiveDonationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String ReceiveDonationExceptionHandler(ReceiveDonationException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DeleteDonationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String DeleteDonationException(DeleteDonationException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String NoSuchElementException(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
