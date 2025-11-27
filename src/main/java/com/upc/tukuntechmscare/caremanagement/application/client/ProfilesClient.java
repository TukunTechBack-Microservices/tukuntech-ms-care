package com.upc.tukuntechmscare.caremanagement.application.client;

import com.upc.tukuntechmscare.caremanagement.application.dto.UserProfileResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Component
public class ProfilesClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ProfilesClient(
            RestTemplate restTemplate,
            @Value("${profiles.service.url}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    private HttpHeaders authHeaders(String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);
        return headers;
    }

    public UserProfileResponse getProfileById(Long id, String bearerToken) {
        String url = baseUrl + "/profiles/" + id;
        HttpEntity<Void> entity = new HttpEntity<>(authHeaders(bearerToken));
        ResponseEntity<UserProfileResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, UserProfileResponse.class);
        return response.getBody();
    }

    public UserProfileResponse getProfileByUserId(Long userId, String bearerToken) {

        String url = baseUrl + "/profiles/by-user/" + userId;
        HttpEntity<Void> entity = new HttpEntity<>(authHeaders(bearerToken));
        ResponseEntity<UserProfileResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, UserProfileResponse.class);
        return response.getBody();
    }
}