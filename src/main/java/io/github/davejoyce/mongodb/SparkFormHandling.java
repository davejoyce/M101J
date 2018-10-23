package io.github.davejoyce.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

public class SparkFormHandling {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        Spark.get("/", ((request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
                Map<String, Object> fruitsMap = new HashMap<>();
                fruitsMap.put("fruits", Arrays.asList("apple", "banana", "orange", "peach"));

                fruitPickerTemplate.process(fruitsMap, writer);
                return writer;
            } catch (Exception e) {
                halt(500);
                return null;
            }
        }));
        Spark.post("/favorite_fruit", ((request, response) -> {
            final String fruit = request.queryParams("fruit");
            return (null == fruit)
                    ? "Why don't you pick one?"
                    : ("Your favorite fruit is: " + fruit);
        }));
    }
}
