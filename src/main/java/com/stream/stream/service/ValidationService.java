package com.stream.stream.service;

import com.stream.stream.exceptions.IncorrectNameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service

public class ValidationService {

    public String validate(String name){
        if(!StringUtils.isAlpha(name)){
            throw new IncorrectNameException();
        }else {
        return StringUtils.capitalize(StringUtils.lowerCase(name));}
    }
}
