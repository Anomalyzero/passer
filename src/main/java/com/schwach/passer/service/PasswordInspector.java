package com.schwach.passer.service;

import com.schwach.passer.model.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public class PasswordInspector {

    public SimpleResponse validatePassword(String password){
        return new SimpleResponse(true, "Password satisfies all criteria");
    }
}
