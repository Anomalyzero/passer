#!/bin/bash

# Examples using the service

# Short password
curl localhost:8080/password-validation -H "Content-Type: text/plain" -d "12t"
echo

# Too Long Password
curl localhost:8080/password-validation -H "Content-Type: text/plain" -d "0123456789abcde"
echo

# Missing Letter
curl localhost:8080/password-validation -H "Content-Type: text/plain" -d "0123456789"
echo

# Missing Digit
curl localhost:8080/password-validation -H "Content-Type: text/plain" -d "abcdefgh"
echo

# Disallowed Capital
curl localhost:8080/password-validation -H "Content-Type: text/plain" -d "Passw0rd"
echo

# Disallowed Special Char
curl localhost:8080/password-validation -H "Content-Type: text/plain" -d "passw0rd!"
echo

# Disallowed Character Sequence Repetition
curl localhost:8080/password-validation -H "Content-Type: text/plain" -d "abcabc12"
echo

# Acceptable Password
curl localhost:8080/password-validation -H "Content-Type: text/plain" -d "passw0rd"
echo