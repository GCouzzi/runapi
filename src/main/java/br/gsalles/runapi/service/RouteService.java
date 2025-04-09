package br.gsalles.runapi.service;

import br.gsalles.runapi.model.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RouteService {

    @Value("${openrouteservice.api.key}")
    private String apiKey;

    @Value("${openrouteservice.base.url}")
    private String baseUrl;

    private final WebClient webClient;

    private final ObjectMapper objectMapper;

    public double getDistance(Location startLocation, Location endLocation){

        String url = String.format("%s?api_key=%s&start=%f,%f&end=%f,%f",
                baseUrl, apiKey, startLocation.getLongitude(), startLocation.getLatitude(), endLocation.getLongitude(), endLocation.getLatitude());

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            System.out.println(jsonNode.toPrettyString());
            double distanceInKm = jsonNode.path("features")
                    .get(0)
                    .path("properties")
                    .path("segments")
                    .get(0)
                    .path("distance")
                    .asDouble();
            return distanceInKm / 1000.0;
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON: " + response);
        }
    }
}


