package com.blogspot.atomskjournal.controller;

import com.blogspot.atomskjournal.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FormValidator implements Validator {
    String AGE_PATTERN = "[0-9]+";
    String STRING_PATTERN = "[a-zA-Z\\s]+";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "required.age");

        if (user.getName().length() > 30){

            errors.rejectValue("name", "name.tooLong");
        }


        pattern = Pattern.compile(STRING_PATTERN);
        matcher = pattern.matcher(user.getName());
        if (!matcher.matches()) {

            errors.rejectValue("name", "name.containNonChar");

        }



        pattern = Pattern.compile(AGE_PATTERN);
        matcher = pattern.matcher(String.valueOf(user.getAge()));
        if (!matcher.matches()) {

            errors.rejectValue("age", "age.incorrect");
        }

        if (user.getAge() <0 || user.getAge() > 120) {

            errors.rejectValue("age", "age.unrealistic");
        }


    }
}
