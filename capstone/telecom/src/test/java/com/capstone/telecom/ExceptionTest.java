package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.capstone.telecom.exception.TelecomBusinessException;

public class ExceptionTest {

     @Test
    public void testTelecomBusinessException() {
        String expectedMessage = "This is a test exception message";

        try {
            throw new TelecomBusinessException(expectedMessage);
        } catch (TelecomBusinessException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
    
}
