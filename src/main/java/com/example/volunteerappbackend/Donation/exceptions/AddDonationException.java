package com.example.volunteerappbackend.Donation.exceptions;

public class AddDonationException extends RuntimeException{
    public AddDonationException(String message) {super("unable to add donation: " + message);}
}
