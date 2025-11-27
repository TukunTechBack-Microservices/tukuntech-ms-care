package com.upc.tukuntechmscare.caremanagement.application.facade;

import com.upc.tukuntechmscare.caremanagement.application.client.ProfilesClient;
import com.upc.tukuntechmscare.caremanagement.application.dto.UserProfileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProfileApplicationFacade {

    private final ProfilesClient profilesClient;

    public ProfileApplicationFacade(ProfilesClient profilesClient) {
        this.profilesClient = profilesClient;
    }

    private String getCurrentBearerToken() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) return null;
        HttpServletRequest request = attrs.getRequest();
        String header = request.getHeader("Authorization");
        return header != null ? header : "";
    }

    public UserProfileResponse getProfileByUserId(Long userId) {
        String token = getCurrentBearerToken();
        return profilesClient.getProfileByUserId(userId, token);
    }

    public UserProfileResponse getProfileById(Long id) {
        String token = getCurrentBearerToken();
        return profilesClient.getProfileById(id, token);
    }
}
