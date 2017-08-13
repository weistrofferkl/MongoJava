package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class HelloWorldFreemarker {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarker.class, "/");

        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl" );
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "Freemarker");
            helloTemplate.process(helloMap,writer);

            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
