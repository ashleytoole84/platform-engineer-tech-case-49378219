package com.example;

import java.util.Random;

public class Welcome {
    public static String welcome(String name) {
        return String.format("Welcome to %s!", name);
    }

    public static boolean sometimesFails() {
        return new Random().nextDouble() > 0.3; // 70% success, 30% fail
    }

    public static String sometimesThrows() {
        if (new Random().nextInt(5) == 0) {
            throw new RuntimeException("random exception occurred");
        }
        return "no exception";
    }

    public static String flakyTestJoke() {
        return "Flaky tests: because sometimes, life needs a little randomness!";
    }

    public static void main(String[] args) {
        System.out.println(welcome("Hazelcast"));
    }
} 