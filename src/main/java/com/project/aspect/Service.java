package com.project.aspect;

import org.springframework.stereotype.Component;

@Component("service")
public class Service {

    public void update(String name) {
        throw  new UnsupportedOperationException();
    }
}
