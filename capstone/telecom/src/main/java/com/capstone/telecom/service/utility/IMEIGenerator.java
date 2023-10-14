package com.capstone.telecom.service.utility;

import java.util.Random;

public class IMEIGenerator {

    public static String generateIMEI() {
        // IMEI numbers are typically 15 digits long
        int length = 15;
        StringBuilder imei = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                // The first digit (Type Allocation Code) is usually set to 0 for testing purposes
                imei.append("0");
            } else {
                imei.append(generateRandomDigit());
            }
        }

        return imei.toString();
    }

    private static String generateRandomDigit() {
        // Generate a random digit from 0 to 9
        Random random = new Random();
        return String.valueOf(random.nextInt(10));
    }
}
