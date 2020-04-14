package com.schwach.passer.model;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PasswordPolicies {
    @NotBlank
    @Value("${policy.minimumLength}")
    private static int minimumLength;

    @NotBlank
    @Value("${policy.maximumLength}")
    private static int maximumLength;

    private static List<PasswordPolicy> tests = new ArrayList<>();

    static {
        // Minimum Length
        Predicate<String> minLenCheck = str -> str.length() < minimumLength;
        tests.add(
                new PasswordPolicy(minLenCheck, String.format("Password does not meet minimum length of %d", minimumLength))
        );
    }

    public static List<PasswordPolicy> getTests() {
        return tests;
    }
}
