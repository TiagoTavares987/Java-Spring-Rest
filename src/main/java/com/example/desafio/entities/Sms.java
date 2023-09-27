package com.example.desafio.entities;

public class Sms {
    private final String phoneNumber;
    private final String message;

    public Sms(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }
}
