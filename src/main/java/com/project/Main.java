package com.project;

import com.project.aspect.Service;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("coc.xml");

        HashMap<String,List<String>> map = (HashMap<String, List<String>>) context.getBean("map");

        System.out.println(map);

        System.out.println(map.get("aaa"));
    }
}
