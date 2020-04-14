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
    @Value("${policy.duplicateSequenceRegex}")
    private String duplicateSequenceRegex;

    @NotBlank
    @Value("${policy.disallowedCharRegex}")
    private String disallowedCharRegex;

    @NotBlank
    @Value("${policy.requiredLowercaseRegex}")
    private String requiredLowercaseRegex;

    @NotBlank
    @Value("${policy.requiredDigitRegex}")
    private String requiredDigitRegex;

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

            // At least one digit required
            Predicate<String> oneDigitCheck = str -> str.matches(requiredDigitRegex);
            tests.add(
                new PasswordPolicy(oneDigitCheck, "At least one digit is required")
            );

            // At least one lower letter
            Predicate<String> oneLetterCheck = str -> str.matches(requiredLowercaseRegex);
            tests.add(
                    new PasswordPolicy(oneLetterCheck, "At least one lowercase letter is required")
            );

            // No disallowed Characters
            Predicate<String> disallowedCharCheck = str -> !str.matches(disallowedCharRegex);
            tests.add(
                    new PasswordPolicy(disallowedCharCheck, "Password contains disallowed characters, only lowercase letters and numbers are permitted")
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
