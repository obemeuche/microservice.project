package com.obeme.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
