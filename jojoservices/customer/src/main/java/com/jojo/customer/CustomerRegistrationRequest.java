package com.jojo.customer;


public record CustomerRegistrationRequest(String firstName,
                                          String lastName,
                                          String email) {
}
