package com.schwach.passer.service;

import com.schwach.passer.model.SimpleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordInspectorTest {

    @Autowired
    private PasswordInspector inspector;

    @Test
    void validatePassword_verifyMinimumLengthExpectFail(){
        SimpleResponse response = inspector.validatePassword("min");

        assertFalse(response.getSuccess());
        assertFalse(response.getMessage().isEmpty());
    }

    @Test
    void validatePassword_verifyMinimumLengthExpectPass(){
        SimpleResponse response = inspector.validatePassword("overmin1");

        assertTrue(response.getSuccess());
        assertFalse(response.getMessage().isEmpty());
    }

    @Test
    void validatePassword_verifyMaximumLengthExpectFail(){
        SimpleResponse response = inspector.validatePassword("overmaximumval");

        assertFalse(response.getSuccess());
        assertFalse(response.getMessage().isEmpty());
    }

    @Test
    void validatePassword_verifyMaximumLengthExpectPass(){
        SimpleResponse response = inspector.validatePassword("undermax1");

        assertTrue(response.getSuccess());
        assertFalse(response.getMessage().isEmpty());
    }

    @Test
    void validatePassword_verifyAllowedCharsExpectFail(){
        List<String> invalidPasswords = new ArrayList<>();
        invalidPasswords.add("password"); // No Numbers
        invalidPasswords.add("Password0"); // Capitals Disallowed
        invalidPasswords.add("passw0rd!"); // Special Characters Disallowed
        invalidPasswords.add("passw0rd@"); // Special Characters Disallowed
        invalidPasswords.add("passw0rd-"); // Special Characters Disallowed
        invalidPasswords.add("passw0rd_"); // Special Characters Disallowed
        invalidPasswords.add("passw 0rd"); // Spaces Disallowed
        invalidPasswords.add("passw\t0rd"); // Tabs Disallowed

        for (String password : invalidPasswords) {
            SimpleResponse response = inspector.validatePassword(password);

            System.out.print("Password '" + password + "' ");
            assertFalse(response.getSuccess());
            assertFalse(response.getMessage().isEmpty());
            System.out.print("passed!\n");
        }
    }

    @Test
    void validatePassword_verifyAllowedCharsExpectPass(){
        List<String> validPasswords = new ArrayList<>();
        validPasswords.add("password1");
        validPasswords.add("012password");

        for (String password : validPasswords) {
            SimpleResponse response = inspector.validatePassword(password);

            assertTrue(response.getSuccess());
            assertFalse(response.getMessage().isEmpty());
        }
    }

    @Test
    void validatePassword_verifyDuplicateSequencesExpectFail(){
        List<String> invalidPasswords = new ArrayList<>();
        invalidPasswords.add("ab123123");
        invalidPasswords.add("1212ab");
        invalidPasswords.add("houndhound2");

        for (String password : invalidPasswords) {
            SimpleResponse response = inspector.validatePassword(password);

            assertFalse(response.getSuccess());
            assertFalse(response.getMessage().isEmpty());
        }
    }

    @Test
    void validatePassword_verifyDuplicateSequencesExpectSuccess(){
        List<String> invalidPasswords = new ArrayList<>();
        invalidPasswords.add("abcd1234");
        invalidPasswords.add("catdog1");
        invalidPasswords.add("b1b2b3b4");
        invalidPasswords.add("hound2");

        for (String password : invalidPasswords) {
            SimpleResponse response = inspector.validatePassword(password);

            assertTrue(response.getSuccess());
            assertFalse(response.getMessage().isEmpty());
        }
    }

}