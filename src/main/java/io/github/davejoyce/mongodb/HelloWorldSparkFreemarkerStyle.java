package io.github.davejoyce.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");


        Spark.get("/", ((request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "Freemarker");

                helloTemplate.process(helloMap, writer);
            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
            }
            return writer;
        }));
    }
}
