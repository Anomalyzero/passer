package com.schwach.passer.service;

import com.schwach.passer.config.PasswordPolicies;
import com.schwach.passer.model.PasswordPolicy;
import com.schwach.passer.model.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordInspector {

    @Autowired
    PasswordPolicies passwordPolicies;

    public SimpleResponse validatePassword(String password){
        List<PasswordPolicy> policies = passwordPolicies.getTests();

        for (PasswordPolicy policy : policies) {
            if(!policy.getTest().test(password)){
                return new SimpleResponse(false, policy.getFailureMessage());
            }
        }
        return new SimpleResponse(true, "Password satisfies all criteria");
    }
}
