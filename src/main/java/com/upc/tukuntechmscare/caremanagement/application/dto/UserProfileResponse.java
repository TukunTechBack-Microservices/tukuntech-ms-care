package com.upc.tukuntechmscare.caremanagement.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserProfileResponse(
        Long id,
        Long userId,
        String firstName,
        String lastName,
        String dni,
        Integer age,
        String gender,
        String bloodGroup,
        String nationality,
        String allergy,
        ProfileType profileType
) {}