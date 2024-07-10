package com.example.volunteerappbackend.Donation.exceptions;

public class ReceiveDonationException extends RuntimeException{
    public ReceiveDonationException(String message) {
        super("unable to receive Donations: " + message);
    }
}
