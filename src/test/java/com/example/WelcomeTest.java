package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WelcomeTest {
    @Test
    void testWelcome() {
        assertEquals("Welcome to Hazelcast!", Welcome.welcome("Hazelcast"));
    }

    @Test
    void testFlakyJoke() {
        assertEquals("Flaky tests: because sometimes, life needs a little randomness!", Welcome.flakyTestJoke());
    }

    // Deliberately flaky test
    @Test
    void testSometimesFails() {
        assertTrue(Welcome.sometimesFails(), "Flaky: sometimesFails should return true");
    }

    // Deliberately flaky test
    @Test
    void testSometimesThrows() {
        try {
            Welcome.sometimesThrows();
        } catch (RuntimeException e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }
} 