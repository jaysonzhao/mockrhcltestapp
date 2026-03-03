package com.example.testpagesapp;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
public class TestPagesController {

    private final Random random = new Random();

    @GetMapping(value = "/pagea", produces = MediaType.TEXT_HTML_VALUE)
    public String pageA() {
        // Generate random HTML content
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("    <title>Random HTML Page</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("    <h1>Random HTML Content</h1>\n")
                .append("    <p>This is a random paragraph with ID: ").append(UUID.randomUUID()).append("</p>\n")
                .append("    <p>Random number: ").append(random.nextInt(1000)).append("</p>\n")
                .append("    <ul>\n");
        
        // Add random list items
        int itemCount = random.nextInt(5) + 3;  // 3 to 7 items
        for (int i = 0; i < itemCount; i++) {
            htmlBuilder.append("        <li>Random item ").append(i + 1).append(": ").append(UUID.randomUUID().toString().substring(0, 8)).append("</li>\n");
        }
        
        htmlBuilder.append("    </ul>\n")
                .append("    <p>Current timestamp: ").append(System.currentTimeMillis()).append("</p>\n")
                .append("</body>\n")
                .append("</html>");
        
        return htmlBuilder.toString();
    }

    @GetMapping("/pageb")
    public ResponseEntity<Object> pageB() {
        // Create random JSON content
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("id", UUID.randomUUID().toString());
        jsonResponse.put("timestamp", System.currentTimeMillis());
        jsonResponse.put("randomNumber", random.nextInt(1000));
        
        // Add random array
        int[] randomArray = new int[random.nextInt(5) + 3];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = random.nextInt(100);
        }
        jsonResponse.put("randomArray", randomArray);
        
        // Add nested object
        Map<String, Object> nestedObject = new HashMap<>();
        nestedObject.put("name", "Random Object " + random.nextInt(100));
        nestedObject.put("value", UUID.randomUUID().toString().substring(0, 8));
        nestedObject.put("active", random.nextBoolean());
        jsonResponse.put("nestedObject", nestedObject);
        
        // Return with custom status code 678
        return ResponseEntity.status(678).body(jsonResponse);
    }
}