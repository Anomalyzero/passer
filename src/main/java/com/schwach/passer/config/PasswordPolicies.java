package com.schwach.passer.config;

import com.schwach.passer.model.PasswordPolicy;
import org.springframework.beans.factory.annotation.Value;
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

    @NotBlank
    @Value("${policy.charRegex}")
    private String charRegex;

    @NotBlank
    @Value("${policy.duplicateSequenceRegex}")
    private String duplicateSequenceRegex;

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

            // Only lowercase letters & numbers
            Predicate<String> allowedCharCheck = str -> str.matches(charRegex);
            tests.add(
                    new PasswordPolicy(allowedCharCheck, "Password must consist of lowercase letters, numbers and at least one of each")
            );

            // No Duplicate character sequences
            Predicate<String> duplicateCharCheck = str -> !str.matches(duplicateSequenceRegex);
            tests.add(
                    new PasswordPolicy(duplicateCharCheck, "Password may not contain repeated character sequences")
            );
        }
        return tests;
    }
}
