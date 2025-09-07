package com.vinsguru.tests.flightreservation.model;

public record FlightReservationTestData (String firstname,
                                        String lastname,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String state,
                                        String zip,
                                        String noOfPassengers,
                                        String expectedPrice) {
}
