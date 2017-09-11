package org.shenme.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MinaRun {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config/spring-mina.xml");
    }

}
