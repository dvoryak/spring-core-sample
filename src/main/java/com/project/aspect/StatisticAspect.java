package com.project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component("statistic")
@Aspect
public class StatisticAspect {

    private Map<Class,Integer> map = new HashMap<>();

    public Map<Class, Integer> getMap() {
        return map;
    }

    @AfterReturning("execution(* com.project.loggers.*.logEvent(..)) " +
            "&& !execution(* com.project.loggers.CombinedEventLogger.logEvent(..))")
    public void statistic(JoinPoint joinPoint) {
        Class<?> key = joinPoint.getTarget().getClass();
        if(map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key,1);
        }
    }
}
