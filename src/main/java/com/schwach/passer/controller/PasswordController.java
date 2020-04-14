package com.schwach.passer.controller;

import com.schwach.passer.model.SimpleResponse;
import com.schwach.passer.service.PasswordInspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

    @Autowired
    PasswordInspector inspector;

    @PostMapping("/password-validation")
    public ResponseEntity<SimpleResponse> validatePassword(@RequestBody String password){

        if(password == null || password.isEmpty()){
            return ResponseEntity.badRequest().body(new SimpleResponse(false, "Password may not be empty!"));
        }

        SimpleResponse response = inspector.validatePassword(password);

        if(response.getSuccess()){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
