package com.project.aspect;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspect {

    @AfterThrowing("execution(* *..Service.*(..))")
    public void update() {
        System.out.println("catch");
    }
}
