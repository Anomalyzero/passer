package com.schwach.passer.model;

import java.util.function.Predicate;

public class PasswordPolicy {
    private Predicate<String> test;
    private String failureMessage;

    public PasswordPolicy() {}
    public PasswordPolicy(Predicate<String> test, String failureMessage) {
        this.test = test;
        this.failureMessage = failureMessage;
    }

    public Predicate<String> getTest() {
        return test;
    }

    public void setTest(Predicate<String> test) {
        this.test = test;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }
}
