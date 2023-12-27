package org.example.kirana_register_project.crud.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class FxRatesService {

    @Value("${fxratesapi.url}")
    private String apiUrl;

    public Map<String, Double> getFxRates() {
        // Make API call
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        // Parse JSON response into a HashMap
        return parseJsonResponse(jsonResponse);
    }

    private Map<String, Double> parseJsonResponse(String jsonResponse) {
        Map<String, Double> ratesMap = new HashMap<>();

        try {
            // Use Jackson ObjectMapper to parse JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // Extract rates from the "rates" node
            JsonNode ratesNode = jsonNode.path("rates");
            Iterator<Entry<String, JsonNode>> fields = ratesNode.fields();

            // Populate the ratesMap
            while (fields.hasNext()) {
                Entry<String, JsonNode> entry = fields.next();
                String currency = entry.getKey();
                double rate = entry.getValue().asDouble();
                ratesMap.put(currency, rate);
            }
        } catch (IOException e) {
            // Handle the exception appropriately (e.g., log it)
            e.printStackTrace();
        }

        return ratesMap;
    }
}
