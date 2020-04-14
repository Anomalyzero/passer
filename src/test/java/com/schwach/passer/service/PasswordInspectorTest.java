package com.schwach.passer.service;

import com.schwach.passer.model.SimpleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordInspectorTest {

    @Autowired
    private PasswordInspector inspector;

    @Test
    void validatePassword_verifyMinimumLength(){
        SimpleResponse response = inspector.validatePassword("min");

        assertFalse(response.getSuccess());
        assertFalse(response.getMessage().isEmpty());
    }
}