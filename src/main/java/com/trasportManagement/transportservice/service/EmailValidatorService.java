package com.trasportManagement.transportservice.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidatorService implements Predicate<String> {
    @Override
    public boolean test(String s) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(s);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
}
