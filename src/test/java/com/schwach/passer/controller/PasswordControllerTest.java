package com.schwach.passer.controller;

import com.schwach.passer.model.SimpleResponse;
import com.schwach.passer.service.PasswordInspector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PasswordControllerTest {
    
    @Autowired
    private PasswordController controller;

    @MockBean
    private PasswordInspector mockInspector;

    @Test
    void validatePassword_failWithNull() {
        ResponseEntity<SimpleResponse> response = controller.validatePassword(null);
        assertFalse(response.getBody().getSuccess());
        assertFalse(response.getBody().getMessage().isEmpty());
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    void validatePassword_failWithEmptyString() {
        ResponseEntity<SimpleResponse> response = controller.validatePassword("");
        assertFalse(response.getBody().getSuccess());
        assertFalse(response.getBody().getMessage().isEmpty());
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    void validatePassword_callsInspector(){
        String password = "password";

        when(mockInspector.validatePassword(password)).thenReturn(new SimpleResponse(true, "All criteria passed!"));

        controller.validatePassword(password);

        verify(mockInspector).validatePassword(password);
    }
}