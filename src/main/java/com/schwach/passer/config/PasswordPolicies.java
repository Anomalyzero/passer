package com.schwach.passer.config;

import com.schwach.passer.model.PasswordPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class PasswordPolicies {
    @NotBlank
    @Value("${policy.minimumLength}")
    private int minimumLength;

    @NotBlank
    @Value("${policy.maximumLength}")
    private int maximumLength;

    private static List<PasswordPolicy> tests = new ArrayList<>();

    public List<PasswordPolicy> getTests() {
        if(tests.isEmpty()) {

            // Minimum Length
            Predicate<String> minLenCheck = str -> str.length() >= minimumLength;
            tests.add(
                    new PasswordPolicy(minLenCheck, String.format("Password is under minimum length of %d", minimumLength))
            );

            // Maximum Length
            Predicate<String> maxLenCheck = str -> str.length() <= maximumLength;
            tests.add(
                    new PasswordPolicy(maxLenCheck, String.format("Password is over maximum length of %d", maximumLength))
            );
        }
        return tests;
    }
}
