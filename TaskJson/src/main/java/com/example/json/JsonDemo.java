package com.example.json;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonDemo {

    public static void main(String[] args) {

        String inputJson = "{\n" +
                "    \"menu\": {\n" +
                "        \"id\": \"file\",\n" +
                "        \"value\": \"File\",\n" +
                "        \"popup\": {\n" +
                "            \"menuitem\": [\n" +
                "                {\n" +
                "                    \"value\": \"New\",\n" +
                "                    \"onclick\": \"CreateDoc()\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"Open\",\n" +
                "                    \"onclick\": \"OpenDoc()\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"Save\",\n" +
                "                    \"onclick\": \"SaveDoc()\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(inputJson);

            JsonNode menuItems = rootNode.path("menu").path("popup").path("menuitem");

            List<Map<String, String>> itemList = new ArrayList<>();

            for (JsonNode menuItem : menuItems) {
                String id = rootNode.path("menu").path("id").asText();
                String name = menuItem.path("value").asText();
                String method = menuItem.path("onclick").asText();

                Map<String, String> itemProperties = new LinkedHashMap<>();
                itemProperties.put("id", id);
                itemProperties.put("name", name);
                itemProperties.put("method", method);

                itemList.add(itemProperties);
            }

          

            
           System.out.println(objectMapper.writeValueAsString(itemList)); 

           
        

          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
